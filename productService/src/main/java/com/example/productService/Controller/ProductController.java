package com.example.productService.Controller;

import com.example.productService.Model.Products;
import com.example.productService.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("getProducts/{id}")
    public ResponseEntity<Products> getProduct(@PathVariable Long id) {
        return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
    }

    @GetMapping("getAllProducts")
    public ResponseEntity<List<Products>> getProduct() {
        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @PostMapping("addProduct")
    public ResponseEntity<String> addProduct(@RequestBody Products product) {
        productService.addProduct(product);
        return new ResponseEntity<>("Added", HttpStatus.CREATED);
    }

    @DeleteMapping("deleteProduct/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id)
    {
        productService.deleteProduct(id);
        return new ResponseEntity<>("Deleted", HttpStatus.OK);
    }

    @GetMapping("getProduct")
    public ResponseEntity<List<Products>> getProductByCategory(@RequestParam String category)
    {
        return new ResponseEntity<>(productService.getProductByCategory(category),HttpStatus.OK);
    }
    @GetMapping("getProduct/{category}")
    public ResponseEntity<List<Products>> getProductByCategoryPath(@PathVariable String category)
    {
        return new ResponseEntity<>(productService.getProductByCategory(category),HttpStatus.OK);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Products product )
    {
        productService.updateProduct(id,product);
        return new ResponseEntity<>("Updated",HttpStatus.OK);
    }

}
