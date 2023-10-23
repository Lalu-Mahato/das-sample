package com.example.dassample.product;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.dassample.product.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {

}
