package com.example.demo.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.repository.Fabricantes;
import com.example.demo.model.Fabricante;

@Controller
@RequestMapping("/fabricantes")
public class FabricantesController {
	
	@Autowired
	private Fabricantes fabricantes;
	
	@GetMapping("")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("LisFabricantes");
		mv.addObject("fabricantes",fabricantes.findAll());
	return mv;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo(){
		ModelAndView mv = new ModelAndView("FrmFabricantes");
		mv.addObject(new Fabricante());
		return mv;
	}
	
	@PostMapping("")
	public ModelAndView salvar(@Validated Fabricante fabricante, Errors erros, RedirectAttributes redirectAttributes){
		ModelAndView mv = new ModelAndView("FrmCadernos");
		mv.addObject("fabricantes", fabricantes.findAll());
		if(erros.hasErrors()){
			return mv;
		}
		try{
			this.fabricantes.save(fabricante);
			return new ModelAndView("redirect:fabricantes");
		}catch(Exception e){return mv;}
	}
	
	@RequestMapping(value ="/excluir/{idFabricante}")
	public String excluirFabricanteByPathVariable(@PathVariable Long idFabricante, HttpServletRequest request, 
					HttpServletResponse response) {
		this.fabricantes.delete(idFabricante);
		return "redirect:/fabricantes";
	}
	
	@RequestMapping("/alterar/{idFabricante}")
	public ModelAndView alterarFabricanteByPathVariable(@PathVariable Long idFabricante, HttpServletRequest request, 
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("FrmFabricantes");
		mv.addObject("fabricantes",fabricantes.findAll());
		Fabricante fabricante = fabricantes.findOne(idFabricante);
		mv.addObject(fabricante);
		return mv;
	}
	
	@RequestMapping(value="{idFabricante}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long idFabricante, RedirectAttributes attributes) {
		fabricantes.delete(idFabricante);
		attributes.addFlashAttribute("mensagem", "Fabricante excluído com sucesso!");
		return "redirect:/fabricantes";
	}

}
