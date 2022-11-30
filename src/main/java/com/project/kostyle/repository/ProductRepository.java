package com.project.kostyle.repository;

import com.project.kostyle.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long>, ProductRepositoryDsl {
    Product findByPno (Long pno);

}
