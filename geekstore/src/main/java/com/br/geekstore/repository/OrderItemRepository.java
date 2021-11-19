package com.br.geekstore.repository;

import java.util.List;

import com.br.geekstore.model.Order;
import com.br.geekstore.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

	List<OrderItem> findByOrder(Order order);

    List<OrderItem> findByOrderIn(List<Order> orders);

}
