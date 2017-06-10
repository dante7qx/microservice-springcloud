package com.hnasys.boot.web.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DemoController {

	@RequestMapping(value = "/tologin", method = RequestMethod.POST)
	public String login(Model model, @RequestParam(name = "username") String username,
			@RequestParam(name = "password") String password) {
		model.addAttribute("username", username);
		return "index";
	}
}
