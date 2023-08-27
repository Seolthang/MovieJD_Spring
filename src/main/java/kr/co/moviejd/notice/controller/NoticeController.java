package kr.co.moviejd.notice.controller;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.co.moviejd.notice.domain.Notice;
import kr.co.moviejd.notice.domain.PageInfo;
import kr.co.moviejd.notice.service.NoticeService;

@Controller
public class NoticeController {

	@Autowired
	private NoticeService service;
	
	@RequestMapping(value="/notice/insert.do", method=RequestMethod.GET)
	public String showInsertForm() {
		return "notice/insert";
	}
	
	@RequestMapping(value="/notice/insert.do", method=RequestMethod.POST)
	public String insertNotice(
			@ModelAttribute Notice notice
			, @RequestParam(value="uploadFile", required=false) MultipartFile uploadFile
			, HttpServletRequest request
			, Model model) {
		try {
			if(uploadFile != null && !uploadFile.getOriginalFilename().equals("")) {
				// 파일 이름
				String fileName = uploadFile.getOriginalFilename();
				
				// (내가 저장한 후 그 경로를 DB에 저장하도록 준비하는것)
				String root = request.getSession().getServletContext().getRealPath("resources");
				
				// 폴더가 없을 경우 자동 생성(내가 업로드한 파일을 저장할 폴더)
				String saveFolder = root + "\\nuploadFiles";
				File folder = new File(saveFolder);
				if(!folder.exists()) {
					folder.mkdir();
				}
				
				// 파일 경로
				String savePath = saveFolder + "\\" + fileName;
				File file = new File(savePath);
				
				// 파일 저장
				uploadFile.transferTo(file);
				
				// 파일 크기
				long fileLength = uploadFile.getSize();
				
				// DB에 저장하기 위해 notice에 데이터를 Set하는 부분이다.
				notice.setNoticeFilename(fileName);
				notice.setNoticeFilepath(savePath);
				notice.setNoticeFilelength(fileLength);
			}
			int result = service.insertNotice(notice);
			if(result > 0) {
				return "redirect:/notice/list.do";
			}else {
				model.addAttribute("msg", "공지사항 등록에 실패하였습니다.");
				model.addAttribute("error", "공지사항 등록 실패");
				model.addAttribute("url", "/notice/insert.do");
				return "common/errorPage";
			}
		} catch (Exception e) {
			model.addAttribute("msg", "관리자에게 문의해주세요");
			model.addAttribute("error", e.getMessage());
			model.addAttribute("url", "/notice/insert.do");
			return "common/errorPage";
		}
	}
	
	@RequestMapping(value="/notice/detail.do", method=RequestMethod.GET)
	public ModelAndView showNoticeDetail(
			@RequestParam("noticeNo") Integer noticeNo
			, ModelAndView mav) {
		try {
			Notice noticeOne = service.selectNoticeByNo(noticeNo);
			if(noticeOne != null) {
				mav.addObject("notice", noticeOne);
				mav.setViewName("notice/detail");
			}else {
				mav.addObject("msg", "공지사항 조회가 완료되지 않았습니다");
				mav.addObject("error", "공지사항 상세조회 실패");
				mav.addObject("url", "/notice/list.do");
				mav.setViewName("common/errorPage");
			}
		} catch (Exception e) {
			mav.addObject("msg", "공지사항 조회가 완료되지 않았습니다");
			mav.addObject("error", e.getMessage());
			mav.addObject("url", "/notice/list.do");
			mav.setViewName("common/errorPage");
		}
		return mav;
	}
	
	@RequestMapping(value="/notice/list.do", method=RequestMethod.GET)
	public String showListForm(Model model, @RequestParam(value="page", required=false, defaultValue="1") Integer currentPage) {
		try {
			int totalCount = service.getListCount();
			PageInfo pInfo = this.getPageInfo(currentPage, totalCount);
			List<Notice> nList = service.selectNoticeList(pInfo);
			if(nList.size() > 0) {
				model.addAttribute("pInfo", pInfo);
				model.addAttribute("nList", nList);
				return "notice/list";
			}else {
				model.addAttribute("msg", "데이터 조회가 되지 않습니다.");
				model.addAttribute("error", "공지사항 목록 조회 실패");
				model.addAttribute("url", "/notice/list.do");
				return "common/errorPage";
			}
		} catch (Exception e) {
			model.addAttribute("msg", "관리자에게 문의해주세요");
			model.addAttribute("error", e.getMessage());
			model.addAttribute("url", "/notice/list.do");
			return "common/errorPage";
		}
	}

	public PageInfo getPageInfo(int currentPage, int totalCount) {
		PageInfo pi = null;
		int recordCountPerPage = 7;
		int naviCountPerPage = 5;
		int naviTotalCount;
		int startNavi;
		int endNavi;
		
		// 자동 형변환과 강제 형변환을 같이 사용해서 처리 할 수 있다.
		naviTotalCount = (int)((double)totalCount/recordCountPerPage + 0.9);
		// Math.ceil((double)totalCount/recordCountPerPage);
		// currentPage값이 1~5일때 startNavi가 1로 고정되도록 구해주는 식
		startNavi = (((int)((double)currentPage/naviCountPerPage + 0.9))-1)*naviCountPerPage + 1;
		endNavi = startNavi + naviCountPerPage - 1;
		// endNavi는 startNavi에 무조건 naviCountPerPage값을 더하므로
		// 실제 최대값보다 커질 수 있음
		if(endNavi > naviTotalCount) {
			endNavi = naviTotalCount;
		}
		
		pi = new PageInfo(currentPage, recordCountPerPage, naviCountPerPage, startNavi, endNavi, totalCount, naviTotalCount);
		return pi;
	}
	
	@RequestMapping(value="/notice/search.do", method=RequestMethod.GET)
	public String searchNoticeList(@RequestParam("searchCondition") String searchCondition, @RequestParam("searchKeyword") String searchKeyword, @RequestParam(value="page", required=false, defaultValue="1") Integer currentPage, Model model) {
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("searchCondition", searchCondition);
		paramMap.put("searchKeyword", searchKeyword);
		int totalCount = service.getListCount(paramMap);
		PageInfo pInfo = this.getPageInfo(currentPage, totalCount);
		List<Notice> searchList = service.searchNoticeByKeyword(pInfo, paramMap);
		
		if(!searchList.isEmpty()) {
			model.addAttribute("searchCondition", searchCondition);
			model.addAttribute("searchKeyword", searchKeyword);
			model.addAttribute("pInfo", pInfo);
			model.addAttribute("sList", searchList);
			return "notice/search";
		}else {
			model.addAttribute("msg", "데이터 조회가 되지 않습니다.");
			model.addAttribute("error", "공지사항 제목 조회 실패");
			model.addAttribute("url", "/notice/list.kh");
			return "common/errorPage";
		}
	}
}
