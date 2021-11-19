package com.br.geekstore.repository;

import java.util.List;

import com.br.geekstore.model.Cart;
import com.br.geekstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

	List<Cart> findAllByUserCart(User user);

}
