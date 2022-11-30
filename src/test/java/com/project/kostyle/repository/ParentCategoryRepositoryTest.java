package com.project.kostyle.repository;

import com.project.kostyle.entity.ParentCategory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
class ParentCategoryRepositoryTest {
    @Autowired
    ParentCategoryRepository parentCategoryRepository;

    @Test
    @DisplayName("상위 카테고리 저장 테스트")
    public void createParentCategory() {
        //given
        ParentCategory parentCategory = ParentCategory.builder()
                .pcno(1L)
                .name("상의")
                .build();
        //when
        ParentCategory saveParentCategory = parentCategoryRepository.save(parentCategory);

        //then
        System.out.println("saveParentCategory = " + saveParentCategory);
    }
}