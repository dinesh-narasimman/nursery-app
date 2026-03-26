package com.example.productService.Service;

import com.example.productService.Dao.ProductRepo;
import com.example.productService.Model.Products;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    public Products getProduct(Long id) {
        return productRepo.findById(id).get();
    }


    public List<Products> getAllProducts() {
        return productRepo.findAll();
    }

    public void addProduct(Products product) {
        productRepo.save(product);
    }

    public void deleteProduct(Long id) {
        Products product = productRepo.findById(id).get();
        productRepo.delete(product);
    }

    public List<Products> getProductByCategory(String category) {
        ArrayList<Products> products=  productRepo.findByCategory(category);
        return products;

    }

    public void updateProduct(Long id, Products product) {
        Products product1= productRepo.findById(id).get();
        product1.setCategory(product.getCategory());
        product1.setName(product.getName());
        product1.setDescription(product.getDescription());
        product1.setPrice(product.getPrice());
        product1.setStock(product.getStock());

        productRepo.save(product1);
    }

    public List<Products> getProducts(List<Long> productIds) {
        List<Products> products= new ArrayList<>();
        for(Long id: productIds)
        {
            products.add(productRepo.findById((id)).get());
        }
        return products;
    }
}
