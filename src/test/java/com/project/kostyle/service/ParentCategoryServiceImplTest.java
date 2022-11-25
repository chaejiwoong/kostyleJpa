package com.project.kostyle.service;

import com.project.kostyle.dto.product.ParentCategoryDto;
import com.project.kostyle.entity.ParentCategory;
import com.project.kostyle.repository.ParentCategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;

import java.util.List;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
class ParentCategoryServiceImplTest {

    @Autowired
    private ParentCategoryService service;

    @Autowired
    private ParentCategoryRepository repository;

    @PersistenceContext
    private EntityManager em;


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

    @Test
    @Transactional
    @Rollback
    public void findAll(){
        //given
        data();
        //when
        List<ParentCategoryDto> result = service.findAll();

        //then
        assertThat(result.size()).isEqualTo(10);
    }

    @Test
    @Transactional
    @Rollback
    public void update() {
        //given
        data();
        ParentCategoryDto dto = ParentCategoryDto.builder()
                .pcno(1L)
                .name("변경카테고리")
                .build();

        //when
        service.update(dto);
        ParentCategory findEntity = em.find(ParentCategory.class, 1L);

        //then
        assertThat(findEntity.getName()).isEqualTo("변경카테고리");
    }

    @Test
    @Transactional
    @Rollback
    public void delete(){
        //given
        data();

        //when
        service.delete(1L);

        //then
        assertThrows(EntityNotFoundException.class, () -> {
            service.findOne(1L);
        });
    }

    public void data(){
        //given
        IntStream.range(0,10).forEach(i -> {
            ParentCategoryDto dto = ParentCategoryDto.builder()
                    .name("테스트" + i)
                    .build();
            ParentCategory entity = ParentCategoryDto.toEntity(dto);

            em.persist(entity);
        });
        em.flush();
        em.clear();
    }
}