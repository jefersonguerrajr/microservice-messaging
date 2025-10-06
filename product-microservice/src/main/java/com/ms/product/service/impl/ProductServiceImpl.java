package com.ms.product.service.impl;

import com.ms.product.model.Message;
import com.ms.product.model.NotFoundException;
import com.ms.product.model.Product;
import com.ms.product.repository.ProductRepository;
import com.ms.product.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<List<Product>> listAllproducts() {
        List<Product> products = productRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(products);
    }

    @Override
    public ResponseEntity<Product> findProductById(long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new NotFoundException("Product not found"));
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    @Override
    public ResponseEntity<Product> saveProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.OK).body(savedProduct);
    }

    @Override
    public ResponseEntity<Product> updateProduct(Product product) {
        Product savedProduct = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.OK).body(savedProduct);
    }

    @Override
    public ResponseEntity<Message> removeProduct(long productId) {
        Product product = productRepository.findById(productId).orElseThrow(() -> new NotFoundException("Product not found by id:" + productId));
        productRepository.delete(product);
        return ResponseEntity.status(HttpStatus.OK).body(Message.builder().title("Removed").description("Product removed successful").build());
    }
}
