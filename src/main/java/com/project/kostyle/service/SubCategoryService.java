package com.project.kostyle.service;

import com.project.kostyle.dto.product.ParentCategoryDto;
import com.project.kostyle.dto.product.SubCategoryDto;

import java.util.List;

public interface SubCategoryService {

    Long create(SubCategoryDto dto);

    List<SubCategoryDto> findAll();

    SubCategoryDto findOne(Long scno);

    Long update(SubCategoryDto dto);

    Long delete(Long scno);
}
