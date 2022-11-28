package com.project.kostyle.service;

import com.project.kostyle.dto.product.ParentCategoryDto;
import com.project.kostyle.dto.product.SubCategoryDto;
import com.project.kostyle.entity.ParentCategory;
import com.project.kostyle.entity.SubCategory;
import com.project.kostyle.repository.ParentCategoryRepository;
import com.project.kostyle.repository.SubCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class SubCategoryServiceImpl implements SubCategoryService {

    private final ParentCategoryRepository parentCategoryRepository;
    private final SubCategoryRepository subCategoryRepository;

    @Override
    @Transactional
    public Long create(SubCategoryDto dto) {
        ParentCategory pc = parentCategoryRepository.findById(dto.getPcno())
                .orElseThrow(EntityNotFoundException::new);
        return subCategoryRepository.save(SubCategoryDto.toEntity(dto, pc)).getScno();
    }

    @Override
    public List<SubCategoryDto> findAll() {
        return subCategoryRepository.findAll().stream()
                .map(SubCategoryDto::of).collect(Collectors.toList());
    }

    @Override
    public SubCategoryDto findOne(Long scno) {
        return SubCategoryDto.of(subCategoryRepository.findById(scno)
                .orElseThrow(EntityNotFoundException::new));
    }

    @Override
    @Transactional
    public Long update(SubCategoryDto dto) {
        SubCategory sc = subCategoryRepository.findById(dto.getScno())
                .orElseThrow(EntityNotFoundException::new);
        ParentCategory pc = parentCategoryRepository.findById(dto.getPcno())
                .orElseThrow(EntityNotFoundException::new);
        sc.changeInfo(dto, pc);
        return sc.getScno();
    }

    @Override
    @Transactional
    public Long delete(Long scno) {
        subCategoryRepository.deleteById(scno);
        return scno;
    }
}
