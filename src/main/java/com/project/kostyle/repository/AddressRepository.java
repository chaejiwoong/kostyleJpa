package com.project.kostyle.repository;

import com.project.kostyle.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address, Long> {

    //배송지 목록
    /*@Query(value = "select a from Address a where a.member.mno = :mno", nativeQuery = true)
    List<Address> findAll(@Param("mno") Long mno);*/

    /*@Query(value = "select a from Address a inner join a.member m") //nativeQuery = true : MySQL 쿼리문 쓰고 싶을 때
    List<Address> result(@Param("mno") Long mno);*/

    List<Address> findByMember_Mno(Long mno); //jpa

    //배송지 상세
    Address findByAno(Long ano);

    //배송지 삭제
    void deleteAddressByAno(Long ano);



    /*// 기본 배송지 조회
    AddressVO findDefaultAddress(Long mno);

    List<AddressVO> addressList(Long mno);

    //배송지 상세
    AddressVO addressDetail(Long ano);

    //배송지 수정
    void updateAddress(AddressVO vo);

    // 배송지 삭제
    void deleteAddress(Long ano);*/

    //기본 배송지 설정
    //void updateDefault(Long ano);

    // 기본배송지 해제
    //void updateDefaultClear(Long mno);

}


