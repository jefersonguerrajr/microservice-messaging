package com.ms.product.controller;

import com.ms.product.model.Message;
import com.ms.product.model.Product;
import com.ms.product.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct() {
        return productService.listAllproducts();
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> searchClientById(@RequestParam long id) {
        return productService.findProductById(id);
    }

    @PostMapping
    public ResponseEntity<Product> saveUser(@RequestBody Product product) {
        return productService.saveProduct(product);
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Message> removeUser(@PathVariable long productId) {
        return productService.removeProduct(productId);
    }

}
