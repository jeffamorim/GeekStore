package com.br.geekstore.repository;

import java.util.List;

import com.br.geekstore.model.Cart;
import com.br.geekstore.model.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

	List<CartItem> findByCartIn(List<Cart> cart);

	List<CartItem> findByCart(Cart cart);

}
