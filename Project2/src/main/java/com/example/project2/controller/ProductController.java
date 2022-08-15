package com.example.project2.controller;

import com.example.project2.model.Api;
import com.example.project2.model.Product;
import com.example.project2.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;


    @GetMapping
    public ResponseEntity getProducts() {
        return ResponseEntity.status(200).body(productService.getProducts());
    }

    @PostMapping
    public ResponseEntity addProducts(@RequestBody @Valid Product product, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }

        if (productService.addProduct(product)) {
            return ResponseEntity.status(200).body(new Api("New product added !", 200));
        } else
            return ResponseEntity.status(400).body(new Api("Product already exist !", 400));
    }

    @PutMapping
    public ResponseEntity updateProducts(@RequestBody @Valid Product product, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        if (!(productService.updateProduct(product))) {
            return ResponseEntity.status(400).body(new Api("The product is not exist", 400));
        }
        return ResponseEntity.status(200).body(new Api("The product is updated", 200));
    }


    @DeleteMapping
    public ResponseEntity deleteProducts(@RequestBody int index, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new Api(errors.getFieldError().getDefaultMessage(), 400));
        }
        if (!(productService.deleteProduct(index))) {
            return ResponseEntity.status(400).body(new Api("Product not exist !", 400));
        }
        return ResponseEntity.status(200).body(new Api("Product deleted !", 200));


    }


}