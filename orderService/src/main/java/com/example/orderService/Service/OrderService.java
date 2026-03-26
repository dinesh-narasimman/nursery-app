package com.example.orderService.Service;

import com.example.orderService.Dao.OrderRepo;
import com.example.orderService.Feign.OrderInterface;
import com.example.orderService.Modal.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    OrderRepo orderRepo;

    @Autowired
    OrderInterface orderInterface;

    public Response createOrder(OrderDto orderDto) {
        List<OrderItem> orderItems= orderDto.getOrderItems();
        List<Long> productIds= new ArrayList<Long>();

        for(OrderItem order:orderItems)
        {
            productIds.add(order.getProductId());

        }
        List<ProductDto> products= orderInterface.getProduct(productIds).getBody();

        double totalPrice=0.00;

        Map<Long,ProductDto> productMap = new HashMap<>();

        for(ProductDto product : products)
        {
            productMap.put(product.getProductId(),product);
        }

        Order order= new Order();

        order.setUserId(orderDto.getUserId());
        order.setOrderDate(LocalDateTime.now());
        order.setStatus(Status.CREATED);

        for(OrderItem item:orderItems)
        {
            ProductDto product=productMap.get(item.getProductId());
            if(product==null)
            {
                throw new RuntimeException("Product Not found"+item.getProductId());
            }
            if(product.getStock()<item.getQuantity())
            {
                throw new RuntimeException("Product Stock Not Available");
            }

            totalPrice+=product.getPrice() * item.getQuantity();

            item.setPrice(product.getPrice());
            item.setOrder(order);
            item.setProductName(product.getName());

        }

        order.setTotalPrice(totalPrice);
        order.setOrderItems(orderItems);
        orderRepo.save(order);

        Response response= new Response();
        response.setOrderId(order.getOrderId());
        response.setTotalPrice(order.getTotalPrice());
        response.setStatus(order.getStatus());
        return response;
    }


    public OrderDto getOrder(Long id) {
        Order ord=orderRepo.findById(id).get();
        OrderDto orderDto=new OrderDto();
        orderDto.setUserId(ord.getUserId());
        orderDto.setOrderItems(ord.getOrderItems());
        orderDto.setStatus(ord.getStatus());
        return orderDto;
    }

    public List<OrderDto> getOrders() {
        List<OrderDto> orderDtos=new ArrayList<>();

        List<Order> orders= orderRepo.findAll();
        for(Order order:orders)
        {
            OrderDto orderDto= new OrderDto();
            orderDto.setUserId(order.getUserId());
            orderDto.setOrderItems(order.getOrderItems());
            orderDto.setStatus(order.getStatus());
            orderDtos.add(orderDto);
        }
        return orderDtos;
    }

    public List<OrderDto> getOrderByUserId(Long id) {
        List<OrderDto> orderDtos=new ArrayList<>();

        List<Order> orders= orderRepo.findByUserId(id);
        for(Order order:orders)
        {
            OrderDto orderDto= new OrderDto();
            orderDto.setUserId(order.getUserId());
            orderDto.setStatus(order.getStatus());
            orderDto.setOrderItems(order.getOrderItems());
            orderDtos.add(orderDto);
        }
        return orderDtos;
    }

    public OrderDto updateStatus(Long orderId, StatusUpdateDto status) {
        Order order= orderRepo.findById(orderId).get();
        order.setStatus(status.getStatus());
        orderRepo.save(order);

        OrderDto orderDto= new OrderDto();
        orderDto.setUserId(order.getUserId());
        orderDto.setStatus(order.getStatus());
        orderDto.setOrderItems(order.getOrderItems());
        return orderDto;
    }
}
