package com.egg.noticias.services.Impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import java.util.stream.Collectors;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
//import org.springframework.web.context.request.RequestContextHolder;
//import org.springframework.web.context.request.ServletRequestAttributes;

import com.egg.noticias.dto.UserDTO;
import com.egg.noticias.entities.Rol;
import com.egg.noticias.entities.Users;
import com.egg.noticias.repositories.UserRepository;
import com.egg.noticias.services.UsersService;

@Service
public class UsersServiceImpl implements UsersService {

	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public UsersServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public Users saveUser(UserDTO userDTO) {
		Users usuario = new Users(userDTO.getNombre(), userDTO.getApellido(),
				userDTO.getEmail(), passwordEncoder.encode(userDTO.getPassword()),
				Arrays.asList(new Rol("ROLE_ADMIN")));
		return userRepository.save(usuario);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users usuario = userRepository.findByEmail(username);

		if (usuario == null) {
			throw new UsernameNotFoundException("Usuario o password inválidos");
		}

		
		return new User(usuario.getEmail(), usuario.getPassword(), mapearAutoridadesRoles(usuario.getRoles()));
	}

	private Collection<? extends GrantedAuthority> mapearAutoridadesRoles(Collection<Rol> roles) {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getNombre())).collect(Collectors.toList());
	}
	

	@Override
	public List<Users> listarUsuarios() {
		return userRepository.findAll();
	}

	@Override
	public Users obtenerUsuarioPorEmail(String email) {
		
		return userRepository.findByEmail(email);
	}

	



}
