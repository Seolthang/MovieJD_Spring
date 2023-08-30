package kr.co.moviejd.notice.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import kr.co.moviejd.notice.domain.Reply;
import kr.co.moviejd.notice.service.ReplyService;

@Controller
@RequestMapping("/reply")
public class ReplyController {
	
	@Autowired
	ReplyService rService;

	@RequestMapping(value="/add.kh", method=RequestMethod.POST)
	public ModelAndView insertReply(ModelAndView mav
			, @ModelAttribute Reply reply
			, HttpSession session) {
		String url = "";
		try {
			String replyWriter = (String)session.getAttribute("memberId");
			reply.setRepNoticeWriter(replyWriter);
			int result = rService.insertReply(reply);
			url = "/notice/detail.do?noticeNo="+reply.getRepNoticeNo();
			if(result > 0) {
				mav.setViewName("redirect:"+url);
			}else {
				mav.addObject("msg", "댓글 등록이 완료되지 않았습니다");
				mav.addObject("error", "댓글 등록 실패");
				mav.addObject("url", url);
				mav.setViewName("common/errorPage");
			}
		} catch (Exception e) {
			mav.addObject("msg", "관리자에게 문의 바랍니다");
			mav.addObject("error", e.getMessage());
			mav.addObject("url", url);
			mav.setViewName("common/errorPage");
		}
		return mav;
	}
	
	@RequestMapping(value="/update.kh", method=RequestMethod.POST)
	public ModelAndView updateReply(ModelAndView mav
			, @ModelAttribute Reply reply
			, HttpSession session) {
		String url = "";
		try {
			String replyWriter = (String)session.getAttribute("memberId");
			if(replyWriter != null && !replyWriter.equals("")) {
				reply.setRepNoticeWriter(replyWriter);
				url = "/notice/detail.do?noticeNo="+reply.getRepNoticeNo();
				int result = rService.updateReply(reply);
				mav.setViewName("redirect:"+url);
			}else {
				mav.addObject("msg", "로그인이 되지 않았습니다");
				mav.addObject("error", "로그인 정보확인 실패");
				mav.addObject("url", "/index.jsp");
				mav.setViewName("common/errorPage");
			}
		} catch (Exception e) {
			mav.addObject("msg", "관리자에게 문의 바랍니다");
			mav.addObject("error", e.getMessage());
			mav.addObject("url", url);
			mav.setViewName("common/errorPage");
		}
		return mav;
	}
}
