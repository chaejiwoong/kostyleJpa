package com.project.kostyle.repository;

import com.project.kostyle.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {

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


