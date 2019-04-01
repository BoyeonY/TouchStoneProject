package com.nsccDAD.touchstone_demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
	
	@GetMapping("/LoginPage")
	public String showLoginPage() {
		return "plain-login";
	}
}
