package com.egg.noticias.dto;

import java.util.Date;


import com.egg.noticias.entities.Users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NewsDTO {

 
    private Long id;

    private String titulo;
    
    private String cuerpo;
   
    private String imagen;
 
    private String autor;
    
    private Users user;

    private Date fechaPublicacion = new Date();
    
   
}
    
    
    