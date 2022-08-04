package com.egg.noticias.repositories;



import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.egg.noticias.entities.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long>{

	public Users findByEmail(String email);

}
