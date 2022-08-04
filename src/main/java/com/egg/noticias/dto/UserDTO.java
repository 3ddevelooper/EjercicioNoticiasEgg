package com.egg.noticias.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
	
	private Long id;
	private String nombre;
	private String apellido;
	private String email;
	private String password;
	
	
	public UserDTO(String nombre, String apellido, String email, String password) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.password = password;
	}

	public UserDTO(String email) {
		super();
		this.email = email;
	}
	
	
}
