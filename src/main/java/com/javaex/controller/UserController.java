package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.service.UserService;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/user", method = { RequestMethod.GET, RequestMethod.POST })
public class UserController {

	@Autowired
	private UserService userService;

	// 로그인 페이지
	@RequestMapping("/loginForm")
	public String loginForm() {

		return "/user/loginForm";
	}

	// 회원가입 페이지
	@RequestMapping("/joinForm")
	public String joinForm() {

		return "/user/joinForm";
	}

	// 회원가입 - 개인 블로그 생성(+미분류 카테고리)
	@RequestMapping("/join")
	public String join(Model model, @ModelAttribute UserVo userVo) {
		System.out.println("UserController: join;");
		System.out.println(userVo);

		model.addAttribute("joinUser", userVo);
		userService.joinUser(userVo);
		return "/user/joinSuccess";
	}

	// 로그인 - 메인 홈
	@RequestMapping("/login")
	public String login(HttpSession session, @ModelAttribute UserVo userVo) {
		System.out.println("UserController: login;");
		System.out.println(userVo);

		UserVo authUser = userService.loginUserInfo(userVo);
		System.out.println(authUser);
		session.setAttribute("authUser", authUser);

		return "/main/index";
	}

	// 로그아웃
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("authUser");
		session.invalidate();

		return "/main/index";
	}

}
