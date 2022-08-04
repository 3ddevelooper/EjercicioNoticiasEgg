package com.egg.noticias.services;


import java.util.List;
import java.util.Optional;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.egg.noticias.dto.NewsDTO;
import com.egg.noticias.entities.News;
import com.egg.noticias.entities.Users;


@Service
public interface NewsService {

	public Optional<News> findById(Long id);

	public Users getUserLogin();

	public News save(NewsDTO noticia, MultipartFile imagen);

	public List<News> getAll();

	public List<News> getAllNewsById(Long id);

	public String nombreUsuario();

	public void eliminar(Long id);

	public News update(NewsDTO noticia, MultipartFile imagen);


}
