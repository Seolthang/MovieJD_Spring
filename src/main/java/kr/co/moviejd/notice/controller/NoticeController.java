package kr.co.moviejd.notice.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import kr.co.moviejd.notice.domain.Notice;
import kr.co.moviejd.notice.domain.PageInfo;
import kr.co.moviejd.notice.domain.Reply;
import kr.co.moviejd.notice.service.NoticeService;
import kr.co.moviejd.notice.service.ReplyService;


@Controller
public class NoticeController {

	@Autowired
	private NoticeService nService;
	@Autowired
	private ReplyService rService;
	
	// ==================== 게시글 작성 페이지 이동 ====================
	@RequestMapping(value="/notice/insert.do", method=RequestMethod.GET)
	public ModelAndView showInsertForm(ModelAndView mav) {
		mav.setViewName("notice/insert");
		return mav;
	}
	// ==================== 게시글 작성 ====================
	@RequestMapping(value="/notice/insert.do", method=RequestMethod.POST)
	public ModelAndView noticeInsert(
			ModelAndView mav
			, @ModelAttribute Notice notice
			, @RequestParam(value="uploadFile", required=false) MultipartFile uploadFile
			, HttpSession session
			, HttpServletRequest request) {
		try {
			String noticeWriter = (String)session.getAttribute("memberId");
			if(noticeWriter != null && !noticeWriter.equals("")) {
				notice.setNoticeWriter(noticeWriter);
				if(uploadFile != null && !uploadFile.getOriginalFilename().equals("")) {
					// 파일정보(이름, 리네임, 경로, 크기) 및 파일저장
					Map<String, Object> nMap = this.saveFile(request, uploadFile);
					notice.setNoticeFilename((String)nMap.get("fileName"));
					notice.setNoticeFileRename((String)nMap.get("fileRename"));
					notice.setNoticeFilepath((String)nMap.get("filePath"));
					notice.setNoticeFilelength((long)nMap.get("fileLength"));
				}
				int result = nService.insertNotice(notice);
				mav.setViewName("redirect:/notice/list.do");
			}else {
				mav.addObject("msg", "게시글 등록이 완료되지 않았습니다");
				mav.addObject("error", "게시글 등록 실패");
				mav.addObject("url", "/board/list.do");
				mav.setViewName("common/errorPage");
			}
		} catch (Exception e) {
			mav.addObject("msg", "관리자에게 문의 바랍니다");
			mav.addObject("error", e.getMessage());
			mav.addObject("url", "/notice/insert.do");
			mav.setViewName("common/errorPage");
		}
		
		return mav;
	}
	// ==================== 게시글 수정 페이지 이동====================
	@RequestMapping(value="/notice/update.do", method=RequestMethod.GET)
	public ModelAndView showNoticeUpdateForm(ModelAndView mav
			, @RequestParam("noticeNo") Integer noticeNo) {
		try {
			Notice notice = nService.selectNoticeByNo(noticeNo);
			mav.addObject("notice", notice);
			mav.setViewName("notice/modify");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return mav;
	}
	
	@RequestMapping(value="/notice/update.do", method=RequestMethod.POST)
	public ModelAndView noticeUpdate(ModelAndView mav
			, @ModelAttribute Notice notice
			, @RequestParam(value="uploadFile", required=false) MultipartFile uploadFile
			, HttpServletRequest request
			, HttpSession session) {
		try {
			String memberId = (String)session.getAttribute("memberId");
			String noticeWriter = notice.getNoticeWriter();
			if(noticeWriter != null && noticeWriter.equals(memberId)) {
				// 수정이라는 과정은 대체하는 것, 대체하는 방법은 삭제 후 등록
				if(uploadFile != null && !uploadFile.getOriginalFilename().equals("")) {
					String fileRename = notice.getNoticeFileRename();
					if(fileRename != null) {
						this.deleteFile(fileRename, request);
					}
					Map<String, Object> nFileMap = this.saveFile(request, uploadFile);
					notice.setNoticeFilename((String)nFileMap.get("fileName"));
					notice.setNoticeFileRename((String)nFileMap.get("fileRename"));
					notice.setNoticeFilepath((String)nFileMap.get("filePath"));
					notice.setNoticeFilelength((long)nFileMap.get("fileLength"));
				}
				int result = nService.updateNoticeByNo(notice);
				if(result > 0) {
					mav.setViewName("redirect:/notice/detail.do?noticeNo="+notice.getNoticeNo());
				}else {
					mav.addObject("msg", "게시글 수정이 완료되지 않았습니다");
					mav.addObject("error", "게시글 수정 실패");
					mav.addObject("url", "/notice/detail.do");
					mav.setViewName("common/errorPage");
				}
			}else {
				mav.addObject("msg", "내가 작성한 글만 수정이 가능합니다");
				mav.addObject("error", "게시글 수정 실패");
				mav.addObject("url", "/notice/list.do");
				mav.setViewName("common/errorPage");
			}
		} catch (Exception e) {
			e.printStackTrace();
			e.getMessage();
			mav.addObject("msg", "게시글 수정이 완료되지 않았습니다");
			mav.addObject("error", e.getMessage());
			mav.addObject("url", "/notice/list.do");
			mav.setViewName("common/errorPage");
		}
		return mav;
	}
	
	private void deleteFile(String fileRename, HttpServletRequest request) {
		// TODO Auto-generated method stub
		String root = request.getSession().getServletContext().getRealPath("resources");
		String delPath = root + "\\nuploadFiles\\" + fileRename;
		File delFile = new File(delPath);
		if(delFile.exists()) {
			delFile.delete();
		}
	}

	// ==================== 게시글 리스트 ====================
	@RequestMapping(value="/notice/list.do", method=RequestMethod.GET)
	public ModelAndView showNoticeList(
			@RequestParam(value="page", required=false, defaultValue="1") Integer currentPage
			, ModelAndView mav) {
		Integer totalCount = nService.getListCount();
		PageInfo pInfo = this.getPageInfo(currentPage, totalCount);
		List<Notice> nList = nService.selectNoticeList(pInfo);
		// 메소드 체이닝 기법으로 연달아 붙여서 사용할 수 있다.
		mav.addObject("nList", nList).addObject("pInfo", pInfo).setViewName("notice/list");
		return mav;
	}
	// ==================== 게시글 상세조회 ====================
	@RequestMapping(value="/notice/detail.do", method=RequestMethod.GET)
	public ModelAndView showNoticeDetail(
			@RequestParam("noticeNo") Integer noticeNo 
			, ModelAndView mav) {
		try {
			Notice noticeOne = nService.selectNoticeByNo(noticeNo);
			if(noticeOne != null) {
				List<Reply> replyList = rService.selectReplyList(noticeNo);
				if(replyList.size() > 0) {
					mav.addObject("rList", replyList);
				}
				mav.addObject("notice", noticeOne);
				mav.setViewName("notice/detail");
			}else {
				mav.addObject("msg", "게시글 조회가 완료되지 않았습니다");
				mav.addObject("error", "게시글 상세조회 실패");
				mav.addObject("url", "/notice/list.do");
				mav.setViewName("common/errorPage");
			}
		} catch (Exception e) {
			mav.addObject("msg", "게시글 조회가 완료되지 않았습니다");
			mav.addObject("error", e.getMessage());
			mav.addObject("url", "/notice/list.do");
			mav.setViewName("common/errorPage");
		}
		return mav;
	}
	// ==================== 게시글 페이징 처리 ====================
	public PageInfo getPageInfo(Integer currentPage, Integer totalCount) {
		int recordCountPerPage = 7;
		int naviCountPerPage = 5;
		int naviTotalCount = (int)Math.ceil((double)totalCount/recordCountPerPage);
		int startNavi = ((int)((double)currentPage/naviCountPerPage+0.9)-1)*naviCountPerPage+1;
		int endNavi = startNavi + naviCountPerPage - 1;
		if(endNavi > naviTotalCount) {
			endNavi = naviTotalCount;
		}
		PageInfo pInfo = new PageInfo(currentPage, totalCount, naviTotalCount, recordCountPerPage, naviCountPerPage, startNavi, endNavi);
		
		return pInfo;
	}
	// ==================== 게시글 첨부파일 업로드 과정 ====================
	public Map<String, Object> saveFile(HttpServletRequest request, MultipartFile uploadFile) throws Exception{
		Map<String, Object> fileMap = new HashMap<String, Object>();
		// resources 경로 구하기
		String root = request.getSession().getServletContext().getRealPath("resources");
		// 파일 저장 경로 구하기
		String savePath = root + "\\nuploadFiles";
		// 파일 이름 구하기
		String fileName = uploadFile.getOriginalFilename();
		// 파일 확장자 구하기
		String extension = fileName.substring(fileName.lastIndexOf(".")+1);
		// 시간으로 파일 리네임하기
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String fileRename = sdf.format(new Date(System.currentTimeMillis()))+"."+extension;
		// 파일 저장 전 폴더 만들기
		File saveFolder = new File(savePath);
		if(!saveFolder.exists()) {
			saveFolder.mkdir();
		}
		// 파일 저장
		File saveFile = new File(savePath+"\\"+fileRename);
		uploadFile.transferTo(saveFile);
		long fileLength = uploadFile.getSize();
		// 파일 정보 리턴
		fileMap.put("fileName", fileName);
		fileMap.put("fileRename", fileRename);
		fileMap.put("filePath", "../resources/nuploadFiles/"+fileRename);
		fileMap.put("fileLength", fileLength);
		return fileMap;
	}
}
