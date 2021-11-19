package com.br.geekstore.repository;

import java.util.Optional;

import com.br.geekstore.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	Optional<Role> findByRole(String role);
}
