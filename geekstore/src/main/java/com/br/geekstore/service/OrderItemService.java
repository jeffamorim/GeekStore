package com.br.geekstore.service;

import java.util.List;

import java.util.Optional;

import com.br.geekstore.dto.OrderItemDTO;
import com.br.geekstore.model.Order;
import com.br.geekstore.model.OrderItem;
import com.br.geekstore.model.User;
import com.br.geekstore.repository.OrderItemRepository;
import com.br.geekstore.repository.OrderRepository;
import com.br.geekstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

	@Autowired
	private UserRepository repositoryUser;

	@Autowired
	private OrderRepository repositoryOrder;

	@Autowired
	private OrderItemRepository repository;

	public ResponseEntity<List<OrderItemDTO>> getAllByUser(String token, Optional<Long> idOrder){
		Optional<User> user = repositoryUser.findByToken(token);
		if(user.isPresent()){
			if(idOrder.isPresent()){
				Optional<Order> order = repositoryOrder.findById(idOrder.get());
				if(order.isPresent() && order.get().getUserOrder().equals(user.get())){
					return ResponseEntity.ok().body(OrderItemDTO.convertList(repository.findByOrder(order.get())));
				} else{
					return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
				}
			}
			List<Order> orders = repositoryOrder.findAllByUserOrder(user.get());
			return ResponseEntity.ok().body(OrderItemDTO.convertList(repository.findByOrderIn(orders)));
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

	public ResponseEntity<List<OrderItemDTO>> getAll(Optional<Long> idOrder){
        Optional<Order> order = repositoryOrder.findById(idOrder.get());
        return ResponseEntity.ok().body(OrderItemDTO.convertList(repository.findByOrder(order.get())));
	}

	public ResponseEntity<OrderItemDTO> getOrderItem(String token, Long id){
		Optional<OrderItem> orderitem = repository.findById(id);
		Optional<User> user = repositoryUser.findByToken(token);
		if(orderitem.isPresent() && user.isPresent()){
			return ResponseEntity.ok().body(OrderItemDTO.convert(orderitem.get()));
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
	}

	public ResponseEntity<OrderItemDTO> getItem(String token, Long id){
		Optional<User> user = repositoryUser.findByToken(token);
		Optional<OrderItem> item = repository.findById(id);
		if(user.isPresent() && item.isPresent()){
			Order order = item.get().getOrder();
			if(order.getUserOrder().equals(user.get())){
				return ResponseEntity.ok().body(OrderItemDTO.convert(item.get()));
			}
		}
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

}
