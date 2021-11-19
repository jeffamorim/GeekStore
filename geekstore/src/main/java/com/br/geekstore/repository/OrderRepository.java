package com.br.geekstore.repository;

import java.util.List;

import com.br.geekstore.model.Order;
import com.br.geekstore.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

	List<Order> findAllByUserOrder(User user);
}
