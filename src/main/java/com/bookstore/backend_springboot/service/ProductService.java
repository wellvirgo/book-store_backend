package com.bookstore.backend_springboot.service;

import com.bookstore.backend_springboot.dto.ProductDetailDTO;
// import com.bookstore.backend_springboot.exception.ResourceNotFoundException;
import com.bookstore.backend_springboot.model.Product;
import com.bookstore.backend_springboot.repository.ProductRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ProductService {
    ProductRepository productRepository;

    public ProductDetailDTO getProductDetail(Long id) {
        Product product = productRepository.findByIdAndIsDeletedFalse(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id " + id));

        return ProductDetailDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .author(product.getAuthor())
                .supplier(product.getSupplier())
                .publisher(product.getPublisher())
                .book_layout(product.getBook_layout())
                .price(product.getPrice())
                .productCode(product.getProductCode())
                .publishYear(product.getPublishYear())
                .language(product.getLanguage())
                .weight(product.getWeight())
                .size(product.getSize())
                .quantityPage(product.getQuantityPage())
                .quantityAvailable(product.getQuantityAvailable())
                .description(product.getDescription())
                .genreName(product.getGenre() != null ? product.getGenre().getName() : null)
                .categoryName(product.getGenre() != null && product.getGenre().getCategory() != null ? 
                        product.getGenre().getCategory().getName() : null)
                .build();
    }
}