package kr.co.moviejd.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.co.moviejd.member.domain.Member;
import kr.co.moviejd.member.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	@RequestMapping(value="/member/enroll.do", method=RequestMethod.GET)
	public String showRegisterForm(Model model) {
		return "member/enroll";
	}
	
	@RequestMapping(value="/member/enroll.do", method=RequestMethod.POST)
	public String insertMember(HttpServletRequest request, HttpServletResponse response
			, @RequestParam("memberId") String memberId
			, @RequestParam("memberPw") String memberPw
			, @RequestParam("memberName") String memberName
			, @RequestParam("memberGender") String memberGender
			, @RequestParam("memberEmail") String memberEmail
			, @RequestParam("memberNickname") String memberNickname
			, Model model) {
		
		Member member = new Member(memberId, memberPw, memberName, memberGender, memberEmail, memberNickname);
		
		try {
			int result = service.insertMember(member);
			if(result > 0) {
				return "redirect:/index.jsp";
			}else {
				model.addAttribute("msg", "회원가입에 실패하였습니다.");
				return "common/errorPage";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorPage";
		}
	}
	@RequestMapping(value="/member/login.do", method=RequestMethod.POST)
	public String selectCheckLogin(
			HttpServletRequest request, HttpServletResponse response
			, @RequestParam("memberId") String memberId
			, @RequestParam("memberPw") String memberPw
			, Model model) {
		
		try {
			Member member = new Member();
			member.setMemberId(memberId);
			member.setMemberPw(memberPw);
			Member mOne = service.selectCheckLogin(member);
			if(mOne != null) {
				model.addAttribute("memberId", mOne.getMemberId());
				model.addAttribute("memberName", mOne.getMemberPw());
				return "redirect:/index.jsp";
			}else {
				model.addAttribute("msg", "아이디와 비밀번호를 다시 확인해주세요.");
				return "common/errorPage";
			}
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", e.getMessage());
			return "common/errorPage";
		}
	}
}
