package kr.co.moviejd.member.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

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
	public String insertMember(@ModelAttribute Member member, Model model) {
		try {
			int result = service.insertMember(member);
			if(result > 0) {
				return "redirect:/index.jsp";
			}else {
				model.addAttribute("msg", "회원가입이 완료되지 않았습니다");
				model.addAttribute("error", "회원가입 실패");
				model.addAttribute("url", "/member/enroll.do");
				return "common/errorPage";
			}
		} catch (Exception e) {
			model.addAttribute("msg", "관리자에게 문의해주세요");
			model.addAttribute("error", e.getMessage());
			model.addAttribute("url", "/member/enroll.do");
			return "common/errorPage";
		}
	}
	@RequestMapping(value="/member/update.do", method=RequestMethod.POST)
	public String updateMember(@ModelAttribute Member member, Model model) {
		try {
			int result = service.updateMember(member);
			if(result > 0) {
				return "redirect:/index.jsp";
			}else {
				model.addAttribute("msg", "회원정보 수정에 실패하였습니다");
				model.addAttribute("error", "회원정보수정 실패");
				model.addAttribute("url", "/member/myInfo.do?memberId"+member.getMemberId());
				return "common/errorPage";
			}
		} catch (Exception e) {
			model.addAttribute("msg", "관리자에게 문의해주세요");
			model.addAttribute("error", e.getMessage());
			model.addAttribute("url", "/index.jsp");
			return "common/errorPage";
		}
	}
	@RequestMapping(value="/member/delete.do", method=RequestMethod.GET)
	public String deleteMember(@RequestParam("memberId") String memberId, Model model) {
		
		int result = service.deleteMember(memberId);
		if(result > 0) {
			return "redirect:/member/logout.do";
		}else {
			model.addAttribute("msg", "회원 탈퇴가 완료되지 않았습니다.");
			return "common/errorPage";
		}
	}
	
	@RequestMapping(value="/member/login.do", method=RequestMethod.GET)
	public String showLoginForm(Model model) {
		return "member/login";
	}
	
	@RequestMapping(value="/member/login.do", method=RequestMethod.POST)
	public String selectCheckLogin(@ModelAttribute Member member, HttpSession session, Model model) {
		
		try {
			int result = service.selectCheckLogin(member);
			if(result > 0) {
				session.setAttribute("memberId", member.getMemberId());
				return "redirect:/index.jsp";
			}else {
				model.addAttribute("msg", "로그인이 완료되지 않았습니다");
				model.addAttribute("error", "로그인 실패");
				model.addAttribute("url", "/member/enroll.do");
				return "common/errorPage";
			}
		} catch (Exception e) {
			model.addAttribute("msg", "관리자에게 문의해주세요");
			model.addAttribute("error", e.getMessage());
			model.addAttribute("url", "/index.jsp");
			return "common/errorPage";
		}
	}
	
	@RequestMapping(value="/member/logout.do", method=RequestMethod.GET)
	public String selectCheckLogout(HttpSession session, Model model) {
		if(session != null) {
			session.invalidate();
			return "redirect:/index.jsp";
		}else {
			model.addAttribute("msg", "로그아웃에 실패하였습니다");
			model.addAttribute("error", "로그아웃 실패");
			model.addAttribute("url", "/index.jsp");
			return "common/errorPage";
		}
	}
	@RequestMapping(value="/member/myInfo.do", method=RequestMethod.GET)
	public String showDetailMember(@RequestParam("memberId") String memberId, Model model) {
		try {
			Member member = service.selectOneById(memberId);
			if(member != null) {
				model.addAttribute("member", member);
				return "member/myInfo";
			}else {
				model.addAttribute("msg", "회원정보 조회에 실패하였습니다");
				model.addAttribute("error", "회원정보 조회 실패");
				model.addAttribute("url", "/index.jsp");
				return "common/errorPage";
			}
		} catch (Exception e) {
			model.addAttribute("msg", "관리자에게 문의해주세요");
			model.addAttribute("error", e.getMessage());
			model.addAttribute("url", "/index.jsp");
			return "common/errorPage";
		}
	}
}
