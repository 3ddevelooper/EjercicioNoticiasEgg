package com.egg.noticias.entities;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotBlank;


import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "noticias")
public class News {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    @NotBlank(message = "El titulo no puede estar vacio.")
    @NotNull(message = "El titulo no puede estar vacio.")
    @Size(min = 10, message = "El titulo de la noticia debe tener entre 10 y 100 caracteres")
    private String titulo;
    
    @Column(nullable = false)
    @NotBlank(message = "El cuerpo no puede estar vacio.")
    @NotNull(message = "El cuerpo no puede estar vacio")
    @Size(min = 100, message = "El cuerpo de la noticia debe tener minimo 100 caracteres")
    private String cuerpo;
    
 
    @Column(nullable = false, columnDefinition="MEDIUMTEXT")
    private String imagen;
    
 
    @Column(nullable = false)
    private String autor;
    
    @ManyToOne()
    @JoinColumn(name= "id_usuario", nullable = false)
    private Users user;

    @Column(unique=false, nullable = false)
    @Temporal(TemporalType.DATE)
    private Date fechaPublicacion;
    

	public News(String titulo, String cuerpo, String imagen, String autor, Users user, Date fechaPublicacion) {
		this.titulo = titulo;
		this.cuerpo = cuerpo;
		this.imagen = imagen;
		this.autor = autor;
		this.user = user;
		this.fechaPublicacion = fechaPublicacion;
	}
}
