package com.bookstore.backend_springboot.controller;

import com.bookstore.backend_springboot.dto.ProductDetailDTO;
import com.bookstore.backend_springboot.service.ProductService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductController {
    ProductService productService;

    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailDTO> getProductDetail(@PathVariable Long id) {
        return ResponseEntity.ok(productService.getProductDetail(id));
    }
}