package com.project.kostyle.service;

import com.project.kostyle.dto.member.AddressDto;
import com.project.kostyle.entity.Member;
import com.project.kostyle.repository.MemberRepository;
import com.project.kostyle.util.SecurityUtil;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddressServiceImplTest {

    @Autowired
    AddressServiceImpl service;

    @Autowired
    MemberRepository memberRepository;

    @PersistenceContext
    private EntityManager em;

    @Test
    @DisplayName("배송지 등록 테스트")
    @Rollback
    public void create(){

        //given
        Member member = memberRepository.findByMno(2L);
        AddressDto addressDto = AddressDto.builder()
                .address("가산동")
                .tel("01022223333")
                .name("집")
                .isDefault(false)
                .recipient("코딩바보에게")
                .build();

        //when
        Long ano = service.createAddress(addressDto, member);

        //then
        Assertions.assertThat(ano).isEqualTo(1L);
    }

    @Test
    @DisplayName("배송지 목록 테스트")
    @Rollback
    public void findAll(){

        //given
//        Long mno = SecurityUtil.getCurrentMemberId(); //2L
//
//        //when
//        service.findAll();
//        //then
    }





}