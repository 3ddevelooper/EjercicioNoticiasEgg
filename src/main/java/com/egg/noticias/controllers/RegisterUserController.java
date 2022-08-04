package com.egg.noticias.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.egg.noticias.dto.UserDTO;
import com.egg.noticias.entities.Users;
import com.egg.noticias.services.UsersService;

@Controller
@RequestMapping("/registro")
public class RegisterUserController {

	@Autowired
	private UsersService usersService;


	@ModelAttribute("usuario")
	public UserDTO retornarUserRegisterDTO() {
		return new UserDTO();
	}


	@GetMapping("")
	public String registro(Model model) {
		model.addAttribute("title", "Registro de usuarios");
		return "registro";
	}


	
	@PostMapping
	public String registrarCuentaDeUsuario(@ModelAttribute("usuario") UserDTO registroDTO) {
		
		Users user  = usersService.obtenerUsuarioPorEmail(registroDTO.getEmail());
	
		if(user!= null && user.getEmail().equals(registroDTO.getEmail())) {
			return "redirect:/registro?error";
		}
		
		if(registroDTO.getNombre().length()<3) {
			return "redirect:/registro?info";
	    }
		
		usersService.saveUser(registroDTO);
		return "redirect:/registro?exito";
		
	}

}
