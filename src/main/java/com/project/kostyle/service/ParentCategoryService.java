package com.project.kostyle.service;

import com.project.kostyle.dto.product.ParentCategoryDto;

import java.util.List;

public interface ParentCategoryService {

    Long create(ParentCategoryDto dto);

    List<ParentCategoryDto> findAll();

    ParentCategoryDto findOne(Long pcno);

    Long update(ParentCategoryDto dto);

    Long delete(Long pcno);
}
