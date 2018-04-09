package com.company.restaulator.areas.supplier.controllers;

import com.company.restaulator.areas.supplier.dtos.OrderDTO;
import com.company.restaulator.areas.supplier.services.OrderService;
import com.company.restaulator.controllers.BaseController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController extends BaseController {

    private static final String ORDERS_VIEW = "suppliers/orders";
    private static final String ORDER_APPROVE_VIEW = "suppliers/approve-order";
    private static final String ORDERS_REDIRECT_VIEW = "orders";
    private static final String ORDERS_KEY = "orders";
    private static final String ORDER_KEY = "order";

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ModelAndView orders() {
        List<OrderDTO> orders = this.orderService.findAllUnapproved();
        return this.view(ORDERS_VIEW, ORDERS_KEY, orders);
    }

    @GetMapping("/approve")
    public ModelAndView approve(@ModelAttribute(ORDER_KEY) OrderDTO orderDTO) {
        orderDTO = this.orderService.findById(orderDTO.getId());
        return this.view(ORDER_APPROVE_VIEW, ORDER_KEY, orderDTO);
    }

    @PostMapping("/approve")
    public ModelAndView approveSubmit(@ModelAttribute(ORDER_KEY) OrderDTO orderDTO) {
        this.orderService.approveOrder(orderDTO);
        return this.redirect(ORDERS_REDIRECT_VIEW);
    }
}