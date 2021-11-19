package com.br.geekstore.repository;

import java.util.List;

import com.br.geekstore.model.Product;
import com.br.geekstore.model.util.Category;
import org.springframework.data.jpa.repository.JpaRepository;




public interface ProductRepository extends JpaRepository<Product, Long> {

	public List<Product> findAllByCategory(Category category);

	public List<Product> findByNameContainingIgnoreCase(String name);

	public List<Product> findAllByDescriptionContainingIgnoreCase(String description);

}
