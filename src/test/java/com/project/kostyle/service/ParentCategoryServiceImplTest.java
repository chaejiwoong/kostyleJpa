package com.project.kostyle.service;

import com.project.kostyle.dto.product.ParentCategoryDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.assertj.core.api.Assertions.*;


@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
class ParentCategoryServiceImplTest {

    @Autowired
    private ParentCategoryService service;

    @PersistenceContext
    EntityManager em;

    @Test
    @Rollback
    public void create(){
        //given
        ParentCategoryDto dto = ParentCategoryDto.builder()
                .name("상의")
                .build();
        //when
        Long pcno = service.create(dto);

        //then
        assertThat(pcno).isEqualTo(1L);
    }
}