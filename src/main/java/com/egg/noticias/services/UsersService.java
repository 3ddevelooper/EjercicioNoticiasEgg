package com.egg.noticias.services;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.egg.noticias.dto.UserDTO;
import com.egg.noticias.entities.Users;

public interface UsersService extends UserDetailsService {

	public Users saveUser(UserDTO userDTO);

	public Users obtenerUsuarioPorEmail(String email);

	public List<Users> listarUsuarios();
	
}
