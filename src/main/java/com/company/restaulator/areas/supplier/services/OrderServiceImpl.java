package com.company.restaulator.areas.supplier.services;

import com.company.restaulator.areas.DTOConverter;
import com.company.restaulator.areas.product.dtos.ProductDTO;
import com.company.restaulator.areas.product.entities.Ingredient;
import com.company.restaulator.areas.product.services.IngredientService;
import com.company.restaulator.areas.product.services.ProductService;
import com.company.restaulator.areas.supplier.dtos.OrderCreateDTO;
import com.company.restaulator.areas.supplier.dtos.OrderDTO;
import com.company.restaulator.areas.supplier.entities.Order;
import com.company.restaulator.areas.supplier.repositories.OrderRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepo;
    private final ProductService productService;

    public OrderServiceImpl(OrderRepository orderRepo, ProductService productService) {
        this.orderRepo = orderRepo;
        this.productService = productService;
    }


    @Override
    public void save(OrderCreateDTO order) {
        Order orderToSave = DTOConverter.convert(order, Order.class);
        orderToSave.setDateOfOrder(new Date());
        BigDecimal totalPrice = new BigDecimal(0);
        for (int i = 0; i < orderToSave.getIngredients().size(); i++) {
            Ingredient currentIngr = orderToSave.getIngredients().get(i);
            currentIngr.setOrdered(true);
            ProductDTO product = this.productService.findById(currentIngr.getProduct().getId());
            BigDecimal currentPrice = product.getPrice().multiply(new BigDecimal(currentIngr.getQuantity()));
            totalPrice = totalPrice.add(currentPrice);
        }
        orderToSave.setTotalPrice(totalPrice);
        this.orderRepo.save(orderToSave);
    }

    @Override
    public List<OrderDTO> findAll() {
        List<Order> orders = this.orderRepo.findAll();
        return orders.stream()
                .map(o -> DTOConverter.convert(o, OrderDTO.class))
                .collect(Collectors.toList());

    }

    @Override
    public void approveOrder(OrderDTO orderDTO) {
        Order order = this.orderRepo.findById(orderDTO.getId());
        order.setApproved(true);
        this.orderRepo.save(order);
    }

    @Override
    public OrderDTO findById(long id) {
        Order order = this.orderRepo.findById(id);
        return DTOConverter.convert(order, OrderDTO.class);
    }

    @Override
    public List<OrderDTO> findAllUnapproved() {
        List<Order> orders = this.orderRepo.findAllUnapproved();
        return orders.stream()
                .map(o -> DTOConverter.convert(o, OrderDTO.class))
                .collect(Collectors.toList());
    }
}