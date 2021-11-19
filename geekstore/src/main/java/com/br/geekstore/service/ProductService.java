package com.br.geekstore.service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.br.geekstore.model.Product;
import com.br.geekstore.dto.ProductDTO;
import com.br.geekstore.model.User;
import com.br.geekstore.model.util.Category;
import com.br.geekstore.repository.ProductRepository;
import com.br.geekstore.repository.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class ProductService {

	@Autowired
	private ProductRepository repository;

	@Autowired
	private UserRepository repositoryUser;

	public ResponseEntity<List<ProductDTO>> findAllProduct(Optional<String> description) {
		if (description.isPresent()) {
			List<Product> products = repository.findAllByDescriptionContainingIgnoreCase(description.get());
			return ResponseEntity.ok().body(ProductDTO.convertList(products));
		}
		return ResponseEntity.status(200).body(ProductDTO.convertList(repository.findAll()));
	}

	public ResponseEntity<ProductDTO> findProductById(Long id) {
		return repository.findById(id).map(resp -> ResponseEntity.ok(ProductDTO.convert(resp)))
				.orElse(ResponseEntity.status(404).build());
	}

	public ResponseEntity<List<ProductDTO>> findProductByCategory(Category category) {
		List<Product> products = repository.findAllByCategory(category);
		if (!products.isEmpty()) {
			return ResponseEntity.status(200).body(ProductDTO.convertList(products));
		} else {
			return ResponseEntity.status(404).build();
		}
	}

	public ResponseEntity<ProductDTO> postProduct(String token, ProductDTO productdto) {
		Optional<User> user = repositoryUser.findByToken(token);
		if (user.isPresent()) {
			Product product = new Product();
			product.setName(productdto.name);
			product.setPrice(productdto.price);
			product.setDescription(productdto.description);
			product.setStock(productdto.stock);
			product.setUrlImage(productdto.urlImage);
			product.setCategory(Category.create(productdto.category));

			return ResponseEntity.status(HttpStatus.CREATED).body(ProductDTO.convert(repository.save(product)));
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

	public ResponseEntity<ProductDTO> updateProduct(String token, ProductDTO productdto, Long id) {
		Optional<User> user = repositoryUser.findByToken(token);
		if (user.isPresent()) {
			Optional<Product> product = repository.findById(id);
			product.get().setName(productdto.name);
			product.get().setPrice(productdto.price);
			product.get().setDescription(productdto.description);
			product.get().setStock(productdto.stock);
			product.get().setUrlImage(productdto.urlImage);
			product.get().setCategory(Category.create(productdto.category));

			return ResponseEntity.status(HttpStatus.CREATED).body(ProductDTO.convert(repository.save(product.get())));
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

	public ResponseEntity<Object> deleteProduct(String token, Long id) {
		Optional<Product> product = repository.findById(id);
		Optional<User> user = repositoryUser.findByToken(token);
		if (user.isPresent() && product.isPresent()) {
			repository.delete(product.get());
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

}
