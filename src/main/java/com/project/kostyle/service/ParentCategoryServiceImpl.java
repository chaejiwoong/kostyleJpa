package com.project.kostyle.service;

import com.project.kostyle.dto.product.ParentCategoryDto;
import com.project.kostyle.entity.ParentCategory;
import com.project.kostyle.repository.ParentCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ParentCategoryServiceImpl implements ParentCategoryService {

    private final ParentCategoryRepository parentCategoryRepository;

    @Override
    @Transactional
    public Long create(ParentCategoryDto dto) {
        return parentCategoryRepository.save(ParentCategoryDto.toEntity(dto)).getPcno();
    }

    @Override
    public List<ParentCategoryDto> findAll() {
        return parentCategoryRepository.findAll().stream()
                .map(ParentCategoryDto::of).collect(Collectors.toList());
    }

    @Override
    public ParentCategoryDto findOne(Long pcno) {
        return ParentCategoryDto.of
                (parentCategoryRepository.findById(pcno)
                        .orElseThrow(EntityNotFoundException::new));
    }

    @Override
    @Transactional
    public Long update(ParentCategoryDto dto) {
        ParentCategory pc = parentCategoryRepository.findById(dto.getPcno())
                .orElseThrow(EntityNotFoundException::new);
        pc.changeName(dto.getName());

        return dto.getPcno();
    }

    @Override
    @Transactional
    public Long delete(Long pcno) {
        parentCategoryRepository.deleteById(pcno);

        return pcno;
    }


}
