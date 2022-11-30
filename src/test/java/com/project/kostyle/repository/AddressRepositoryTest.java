package com.project.kostyle.repository;

import com.project.kostyle.entity.Address;
import groovy.util.logging.Log4j;
import groovy.util.logging.Log4j2;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Rollback
@Log4j2
class AddressRepositoryTest {

    @Autowired
    AddressRepository repository;

    @Test
    @DisplayName("배송지 목록 조회 테스트")
    @Rollback
    public void find(){

        //given
        Long mno = 2L;

        //when
        List<Address> findAll = repository.findByMember_Mno(mno);

        //then
        //assertEquals(findAll.get(0).getMember().getMno(), mno);
        //assertEquals(findAll.get(1).getMember().getMno(), mno);
        Assertions.assertThat(findAll.get(0).getMember().getMno()).isEqualTo(2L);
        Assertions.assertThat(findAll.get(1).getMember().getMno()).isEqualTo(2L);

    }




}