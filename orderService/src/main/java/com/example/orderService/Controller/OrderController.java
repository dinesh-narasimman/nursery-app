package com.example.orderService.Controller;

import com.example.orderService.Modal.*;
import com.example.orderService.Service.OrderService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("createOrder")
    public ResponseEntity<Response> createOrder(@RequestBody OrderDto orderDto)
    {
        return new ResponseEntity<>(orderService.createOrder(orderDto), HttpStatus.CREATED);
    }

    @GetMapping("getOrder/{id}")
    public ResponseEntity<OrderDto> getOrder(@PathVariable Long id)
    {
        return new ResponseEntity<>(orderService.getOrder(id),HttpStatus.OK);
    }

    @GetMapping("getOrders")
    public ResponseEntity<List<OrderDto>> getOrders()
    {
        return new ResponseEntity<>(orderService.getOrders(),HttpStatus.OK);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<List<OrderDto>> getOrderByUserId(@PathVariable Long id)
    {
        return new ResponseEntity<>(orderService.getOrderByUserId(id),HttpStatus.OK);
    }

    @PutMapping("/{orderId}/status")
    public ResponseEntity<OrderDto> updateOrderStatus(@PathVariable Long orderId, @RequestBody StatusUpdateDto status )
    {
        return new ResponseEntity<>(orderService.updateStatus(orderId,status),HttpStatus.OK);
    }
}
