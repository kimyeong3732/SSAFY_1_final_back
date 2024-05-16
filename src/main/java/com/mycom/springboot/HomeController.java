package com.mycom.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

	@GetMapping("/index")
	public String index() {
		return "/index.html";
	}
	
	@GetMapping("/login")
	public String login() {
		return "/login.html";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "/login.html";
	}
	
	@GetMapping("/users")
	public String users() {
		return "/register.html";
	}
	
	@GetMapping("/boardMain")
	public String board() {
		return "/board.html";
	}
	
	@GetMapping("/map")
	public String map() {
		return "/map.html";
	}
	
	@GetMapping("/myPage")
	public String myPage() {
		return "/myPage.html";
	}
}

