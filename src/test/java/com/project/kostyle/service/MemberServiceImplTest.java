package com.project.kostyle.service;

import com.project.kostyle.dto.member.MemberDto;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceImplTest {

    @Autowired
    MemberServiceImpl service;

    @PersistenceContext
    private EntityManager em;


    @Test
    @DisplayName("회원가입 테스트")
    @Rollback
    public void create(){
        //given
        MemberDto memberDto = MemberDto.builder()
                .email("a@naver.com")
                .name("코딩괴물")
                .password("1234")
                .build();

        //when
        Long mno = service.create(memberDto);

        //then
        Assertions.assertThat(mno).isEqualTo(1L);
    }




}