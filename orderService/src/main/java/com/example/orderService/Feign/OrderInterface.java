package com.example.orderService.Feign;


import com.example.orderService.Modal.ProductDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "PRODUCTSERVICE")
public interface OrderInterface {
    @PostMapping("/product/getProducts")
    public ResponseEntity<List<ProductDto>> getProduct(@RequestBody List<Long> productIds);
}
