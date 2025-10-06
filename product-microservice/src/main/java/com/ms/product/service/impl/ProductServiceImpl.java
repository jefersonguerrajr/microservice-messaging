package com.ms.product.service.impl;

import com.google.gson.Gson;
import com.ms.product.model.Message;
import com.ms.product.model.NotFoundException;
import com.ms.product.model.OrderDTO;
import com.ms.product.model.Product;
import com.ms.product.produces.ProductProduces;
import com.ms.product.repository.ProductRepository;
import com.ms.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductProduces productProduces;

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

    @Override
    public void updateProductAmmount(String message){
        OrderDTO order = new Gson().fromJson(message, OrderDTO.class);
        Optional<Product> productOptional = productRepository.findById(order.getProductId());

        if(productOptional.isEmpty()){
            productProduces.sendProductRequest("product not found");
        } else if(productOptional.get().getAmount() == 0 || productOptional.get().getAmount() < order.getAmmount()){
            productProduces.sendProductRequest("product out of stock");
        } else {
            Product product = productOptional.get();
            product.setAmount(product.getAmount() - order.getAmmount());
            productRepository.save(product);
        }

    }
}
