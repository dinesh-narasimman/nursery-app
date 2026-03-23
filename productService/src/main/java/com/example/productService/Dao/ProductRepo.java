package com.example.productService.Dao;

import com.example.productService.Model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ProductRepo extends JpaRepository<Products,Long> {
    ArrayList<Products> findByCategory(String category);
}
