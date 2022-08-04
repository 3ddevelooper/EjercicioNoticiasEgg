package com.egg.noticias.controllers;


import java.util.Optional;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.egg.noticias.dto.NewsDTO;
import com.egg.noticias.entities.News;
import com.egg.noticias.entities.Users;
import com.egg.noticias.services.NewsService;

@Controller
@RequestMapping("admin")
public class NewsController {

	@Autowired
	private NewsService newsService;

	@GetMapping("/publicar")
	public String nuevaNoticia(Model model) {
		
		model.addAttribute("title", "Nueva Noticia");
		model.addAttribute("retorno", newsService.nombreUsuario());
		model.addAttribute("noticia", new News());

		return "nueva-noticia";
	}
	
	@PostMapping("/guardar-noticia")
	@Transactional
	public String guardarNoticia(@Valid @ModelAttribute NewsDTO noticia, BindingResult result, Model model,
			@RequestParam("file") MultipartFile imagen, RedirectAttributes attribute) {

		model.addAttribute("noticia", noticia);

		if(noticia.getTitulo().length()<10) {
			return "redirect:/admin/publicar?errorTitulo";
		}
		if(noticia.getCuerpo().length()<100) {
			return "redirect:/admin/publicar?errorCuerpo";
		}

		newsService.save(noticia, imagen);
		attribute.addFlashAttribute("success", "Publicación creada");
		return "redirect:/home";
	}

	@PostMapping("/actualizar-noticia")
	@Transactional
	public String actualizarNotica(@Valid @ModelAttribute NewsDTO noticia, BindingResult result, Model model,
			@RequestParam("file") MultipartFile imagen, RedirectAttributes attribute) {

		model.addAttribute("noticia", noticia);

		if(noticia.getTitulo().length()<10) {
			return "redirect:/admin/editar/"+noticia.getId()+"?errorTitulo";
		}
		if(noticia.getCuerpo().length()<100) {
			return "redirect:/admin/editar/"+noticia.getId()+"?errorCuerpo";
		}

		newsService.update(noticia, imagen);
		attribute.addFlashAttribute("success", "Publicación creada");
		return "redirect:/home";
	}

	@GetMapping("/completa/{id}")
	public String verNoticia(@PathVariable Long id, Model model) {
		Optional<News> noticia = newsService.findById(id);
		Users usuario = newsService.getUserLogin();
		model.addAttribute("title", "Noticia Completa");
		model.addAttribute("noticia", noticia.get());
		model.addAttribute("retorno", newsService.nombreUsuario());
		model.addAttribute("usuario", usuario);
		return "noticia-completa";
	}
	
	@GetMapping("/editar/{id}")
	public String editarNoticia(@PathVariable Long id, Model model) {
		News noticia = newsService.findById(id).get();
		Users usuario = newsService.getUserLogin();
		model.addAttribute("title", "Editar Noticia");
		model.addAttribute("noticia", noticia);
		model.addAttribute("retorno", newsService.nombreUsuario());
		model.addAttribute("usuario", usuario);
		return "editar-noticia";
	}
	
	

	@GetMapping("/eliminar")
	public String eliminarNoticia(@RequestParam("id") Long id) {
		newsService.eliminar(id);
		return "redirect:/home";
	}
	
	@GetMapping("/mis-publicaciones")
	public String publicaciones(Model model) {
		Users usuario = newsService.getUserLogin();
		model.addAttribute("title", "Mis Publicaciones");
		model.addAttribute("retorno", newsService.nombreUsuario());
		model.addAttribute("noticias", newsService.getAllNewsById(usuario.getId()));
		return "mis-publicaciones";
	}

}
