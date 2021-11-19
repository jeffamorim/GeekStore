package com.br.geekstore.service;

import java.util.List;

import java.util.Optional;

import com.br.geekstore.dto.CartDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.br.geekstore.model.Cart;
import com.br.geekstore.model.CartItem;
import com.br.geekstore.model.User;
import com.br.geekstore.repository.CartRepository;

import com.br.geekstore.repository.UserRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository repository;

	@Autowired
	private UserRepository repositoryUser;

	public ResponseEntity<List<CartDTO>> findAll(String token) {
		Optional<User> user = repositoryUser.findByToken(token);
		if (user.isPresent()) {
			return ResponseEntity.ok().body(CartDTO.convertList(repository.findAllByUserCart(user.get())));
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

	public ResponseEntity<CartDTO> postCart(String token) {
		Optional<User> user = repositoryUser.findByToken(token);
		if (user.isPresent()) {
			Cart cart = new Cart();
			cart.setUserCart(user.get());
			return ResponseEntity.ok().body(CartDTO.convert(repository.save(cart)));
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

	public ResponseEntity<CartDTO> getCart(String token, Long id) {
		Optional<User> user = repositoryUser.findByToken(token);
		Optional<Cart> cart = repository.findById(id);
		if (user.isPresent()) {
			if(cart.isPresent() && cart.get().getUserCart().equals(user.get())) {
				return ResponseEntity.ok().body(CartDTO.convert(cart.get()));
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

	public ResponseEntity<List<CartItem>> getCartItems(String token, Long id){
		Optional<User> user = repositoryUser.findByToken(token);
		Optional<Cart> cart = repository.findById(id);
		if(user.isPresent()) {
			if(cart.isPresent() && cart.get().getUserCart().equals(user.get())) {
				return ResponseEntity.ok().body(cart.get().getItems());
			} else {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

	public ResponseEntity<Object> deleteCart(String token, Long id) {
		Optional<User> user = repositoryUser.findByToken(token);
		Optional<Cart> cart = repository.findById(id);
		if (user.isPresent()) {
			if (cart.isPresent()) {
				if (cart.get().getUserCart().equals(user.get())) {
					repository.delete(cart.get());
					return ResponseEntity.ok().build();
				} else {
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
				}
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

}
