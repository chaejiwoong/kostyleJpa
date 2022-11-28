package com.project.kostyle.service;

import com.project.kostyle.dto.product.ProductDto;
import com.project.kostyle.dto.product.SearchDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ProductService {

    Long create(ProductDto dto);

    Page<ProductDto> findAll(Pageable pageable, SearchDto search);

    ProductDto findOne(Long pno, Cookie cookie, HttpServletResponse response);

    Long update(ProductDto dto);

    Long delete(Long pno);
}
