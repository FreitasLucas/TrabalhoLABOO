package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class AviocommController {
	@RequestMapping("")
	public String home(){
	return "home";
}
	
@RequestMapping(value = "/login", method = RequestMethod.GET)
public String login(Model model, String error, String logout) {
	if (error != null)
		model.addAttribute("error", "Seu nome de usuário e/ou senha está errado");
	if (logout != null)
		model.addAttribute("message", "Você foi deslogado com sucesso");
	return "login";
	}
}
