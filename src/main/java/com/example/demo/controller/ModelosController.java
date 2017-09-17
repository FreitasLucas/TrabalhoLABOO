package com.example.demo.controller;

import java.util.List;

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
import com.example.demo.repository.Modelos;
import com.example.demo.model.Fabricante;
import com.example.demo.model.Modelo;

@Controller
@RequestMapping("/modelos")
public class ModelosController {

	
	@Autowired
	private Modelos modelos;
	@Autowired
	private Fabricantes fabricantes;
	
	@GetMapping("")
	public ModelAndView listar() {
		ModelAndView mv = new ModelAndView("LisModelo");
		mv.addObject("modelos",modelos.findAll());
	return mv;
	}
	
	@RequestMapping("/novo")
	public ModelAndView novo(){
		ModelAndView mv = new ModelAndView("FrmModelo");
		List<Fabricante> allfabricantes= fabricantes.findAllFabricantesinOrder();
		mv.addObject(new Modelo());
		mv.addObject("allfabricantes",allfabricantes);
		return mv;
	}
	
	@PostMapping("")
	public ModelAndView salvar(@Validated Modelo modelo, Errors erros, RedirectAttributes redirectAttributes){
		ModelAndView mv = new ModelAndView("FrmModelo");
		mv.addObject("modelos", modelos.findAll());
		List<Fabricante> allfabricantes= fabricantes.findAllFabricantesinOrder();
		mv.addObject("allfabricantes",allfabricantes);
		if(erros.hasErrors()){
			return mv;
		}
		try{
			this.modelos.save(modelo);
			return new ModelAndView("redirect:modelos");
		}catch(Exception e){return mv;}
		
	}
	
	@RequestMapping(value ="/excluir/{idModelo}")
	public String excluirModeloByPathVariable(@PathVariable Long idModelo, HttpServletRequest request, 
					HttpServletResponse response) {
		this.modelos.delete(idModelo);
		return "redirect:/modelos";
	}
	
	@RequestMapping("/alterar/{idModelo}")
	public ModelAndView alterarModeloByPathVariable(@PathVariable Long idModelo, HttpServletRequest request, 
			HttpServletResponse response) {
		ModelAndView mv = new ModelAndView("FrmModelo");
		List<Fabricante> allfabricantes= fabricantes.findAllFabricantesinOrder();
		mv.addObject("modelos",modelos.findAll());
		Modelo modelo = modelos.findOne(idModelo);
		mv.addObject("allfabricantes",allfabricantes);
		mv.addObject(modelo);
		return mv;
	}
	
	@RequestMapping(value="{idModelo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long idModelo, RedirectAttributes attributes) {
		modelos.delete(idModelo);
		attributes.addFlashAttribute("mensagem", "Modelo excluído com sucesso!");
		return "redirect:/modelos";
	}
}
