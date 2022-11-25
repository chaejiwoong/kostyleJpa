package com.project.kostyle.service;

import com.project.kostyle.dto.product.ProductDto;
import com.project.kostyle.dto.product.SearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Long create(ProductDto dto);

    Page<ProductDto> findAll(Pageable pageable, SearchDto search);
}
