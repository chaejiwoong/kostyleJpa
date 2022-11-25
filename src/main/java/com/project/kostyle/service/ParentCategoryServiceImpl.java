package com.project.kostyle.service;

import com.project.kostyle.dto.product.ParentCategoryDto;
import com.project.kostyle.repository.ParentCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParentCategoryServiceImpl implements ParentCategoryService {

    private final ParentCategoryRepository parentCategoryRepository;

    @Override
    public Long create(ParentCategoryDto dto) {
        return parentCategoryRepository.save(ParentCategoryDto.toEntity(dto)).getPcno();
    }
}
