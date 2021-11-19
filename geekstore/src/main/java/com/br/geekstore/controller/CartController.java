package com.br.geekstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.geekstore.dto.CartDTO;
import com.br.geekstore.model.CartItem;
import com.br.geekstore.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService service;

	@GetMapping
	public ResponseEntity<List<CartDTO>> findAll(@RequestHeader("Authorization") String token) {
		return service.findAll(token);
	}

	@GetMapping("/{id}")
	public ResponseEntity<CartDTO> getCart(@RequestHeader("Authorization") String token, @PathVariable Long id) {
		return service.getCart(token, id);
	}

	@GetMapping("/item/{id}")
	public ResponseEntity<List<CartItem>> getItensCarrinho(@RequestHeader("Authorization") String token, @PathVariable Long id){
		return service.getCartItems(token, id);
	}

	@PostMapping
	public ResponseEntity<CartDTO> postCart(@RequestHeader("Authorization") String token) {
		return service.postCart(token);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteCart(@RequestHeader("Authorization") String token, @PathVariable Long id) {
		return service.deleteCart(token, id);
	}

}
