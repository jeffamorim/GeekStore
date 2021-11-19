package com.br.geekstore.repository;

import java.util.List;
import java.util.Optional;

import com.br.geekstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findAllByNameContainingIgnoreCase(String name);
	Optional<User> findByEmail(String email);
	Optional<User> findByToken(String token);
}
