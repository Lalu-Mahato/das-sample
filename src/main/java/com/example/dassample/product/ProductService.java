package com.example.dassample.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.dassample.product.dto.CreateProductDTO;
import com.example.dassample.product.entity.Product;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public ResponseEntity<Object> findAll() {
        List<Product> products = productRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    public ResponseEntity<Object> create(CreateProductDTO createProductDTO) {
        Product product = new Product();
        product.setCode(createProductDTO.getCode());
        product.setName(createProductDTO.getName());
        product.setDescription(createProductDTO.getDescription());

        Product response = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

}
