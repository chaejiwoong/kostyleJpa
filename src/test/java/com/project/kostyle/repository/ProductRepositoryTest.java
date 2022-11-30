package com.project.kostyle.repository;

import com.project.kostyle.entity.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
class ProductRepositoryTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    @DisplayName("제품 등록 테스트")
    public void createProduct() {
        Product product = Product.builder()
                .pno(1L)
                .amount(10)
                .hitCount(4)
                .name("구몽")
                .price(10000)
                .build();
        Product saveProduct = productRepository.save(product);

        System.out.println("saveProduct = " + saveProduct);
    }
}