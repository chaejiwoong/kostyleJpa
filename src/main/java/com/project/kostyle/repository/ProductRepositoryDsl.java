package com.project.kostyle.repository;

import com.project.kostyle.dto.product.SearchDto;
import com.project.kostyle.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepositoryDsl {

    Page<Product> findAllByDsl(Pageable pageable, SearchDto searchDto);
}
