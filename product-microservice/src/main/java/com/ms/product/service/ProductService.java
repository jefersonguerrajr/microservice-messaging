package com.ms.product.service;

import com.ms.product.model.Message;
import com.ms.product.model.Product;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ProductService {

    ResponseEntity<List<Product>> listAllproducts();

    ResponseEntity<Product> findProductById(long id);

    ResponseEntity<Product> saveProduct(Product product);

    ResponseEntity<Product> updateProduct(Product product);

    ResponseEntity<Message> removeProduct(long productId);
}
