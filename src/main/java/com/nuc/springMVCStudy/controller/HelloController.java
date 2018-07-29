package com.nuc.springMVCStudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path="/mvc")
public class HelloController {

	@RequestMapping("/homez")
	public String home() {
		return "home";
	}
}
