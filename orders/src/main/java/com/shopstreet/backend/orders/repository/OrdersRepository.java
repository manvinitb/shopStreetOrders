package com.shopstreet.backend.orders.repository;

import com.shopstreet.backend.orders.dao.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrdersRepository extends CrudRepository<Order,Long> {

    public List<Order> findAllByUseridOrderByIdDesc(Long userid);

}
