package com.egg.noticias.repositories;


import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.egg.noticias.entities.News;

@Repository
public interface NewsRepository extends JpaRepository<News, Long>{

	@Query(value="select * from noticias n where n.id_usuario = :id", nativeQuery=true)
	public List<News> findAllNewsById(@Param("id") Long id);

}
