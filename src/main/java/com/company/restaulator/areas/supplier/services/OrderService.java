package com.company.restaulator.areas.supplier.services;

import com.company.restaulator.areas.supplier.dtos.OrderCreateDTO;
import com.company.restaulator.areas.supplier.dtos.OrderDTO;

import java.util.List;

public interface OrderService {
    void save(OrderCreateDTO order);
    List<OrderDTO> findAll();

    void approveOrder(OrderDTO orderDTO);

    OrderDTO findById(long id);

    List<OrderDTO> findAllUnapproved();
}