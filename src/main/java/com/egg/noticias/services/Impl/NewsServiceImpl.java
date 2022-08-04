package com.egg.noticias.services.Impl;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.egg.noticias.dto.NewsDTO;
import com.egg.noticias.entities.News;
import com.egg.noticias.entities.Users;
import com.egg.noticias.repositories.NewsRepository;
import com.egg.noticias.services.NewsService;
import com.egg.noticias.services.UsersService;

import net.iharder.Base64;

@Service
public class NewsServiceImpl implements NewsService {

	private final NewsRepository newsrepository;

	private final UsersService userService;

	public NewsServiceImpl(NewsRepository newsrepository, UsersService userService) {
		this.newsrepository = newsrepository;
		this.userService = userService;
	}


	public Optional<News> findById(Long id) {
		return newsrepository.findById(id);
	}

	public Users getUserLogin() {

		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetails = null;
		if (principal instanceof UserDetails) {
			userDetails = (UserDetails) principal;
		}
		return userService.obtenerUsuarioPorEmail(userDetails.getUsername());
	}

	public News save(NewsDTO noticiaDTO, MultipartFile imagen) {
		
		String image="";
		if (!imagen.isEmpty()) {
			try {
				image = Base64.encodeBytes(imagen.getBytes());
			} catch (IOException e) {
				image="No hay imagen";
				e.printStackTrace();
			}
		}
		
		Users usuario = getUserLogin();
		String autor = usuario.getNombre() + " " + usuario.getApellido();
		News noticia = new News(noticiaDTO.getTitulo().toLowerCase(),noticiaDTO.getCuerpo(),
				image,autor,usuario,new Date());

		return newsrepository.save(noticia);
	}

	
	public List<News> getAll() {
		return newsrepository.findAll();
	}

	public List<News> getAllNewsById(Long id) {
		return newsrepository.findAllNewsById(id);
	}

	public String nombreUsuario() {
		String retorno;

		try {
			retorno = "Bienvenido " + getUserLogin().getNombre();
		} catch (NullPointerException e) {
			retorno = "";
		}

		return retorno;
	}

	public void eliminar(Long id) {
		newsrepository.deleteById(id);
	}



	public News update(NewsDTO noticiaDTO, MultipartFile imagen) {

		String image="";
		if (!imagen.isEmpty()) {
			
			try {
				image = Base64.encodeBytes(imagen.getBytes());
			} catch (IOException e) {
				image="No hay imagen";
				e.printStackTrace();
			}
		}

		News news = findById(noticiaDTO.getId()).get();
		
		news.setTitulo(noticiaDTO.getTitulo().toLowerCase());
		news.setCuerpo(noticiaDTO.getCuerpo());
		news.setImagen(image);
		news.setFechaPublicacion(new Date());
		
		return newsrepository.save(news);

	}

}
