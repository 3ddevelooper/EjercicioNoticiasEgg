package com.egg.noticias.controllers;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.egg.noticias.entities.News;
import com.egg.noticias.services.NewsService;


@Controller
@RequestMapping({"/", "home"})
public class HomeController {
	
	@Autowired
	private NewsService newsService;
	

    @GetMapping("")
    public String index(Model model) {
    	
        model.addAttribute("title", "Noticias EGG");
        model.addAttribute("retorno", newsService.nombreUsuario());
        model.addAttribute("description", "Portal De Noticias de EGG Educación");
        model.addAttribute("noticias", newsService.getAll());
       
        return "home";
    }
    
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Iniciar Sesión");
        return "login";
    }
    
    @GetMapping("/lectura/{id}")
    public String editar(@PathVariable Long id, Model model) {
        Optional<News> noticia = newsService.findById(id);
        model.addAttribute("title", "Noticia Completa");
        model.addAttribute("noticia", noticia.get());
        return "noticia-completa";
    }

}
