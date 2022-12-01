package com.project.kostyle.service;

import com.project.kostyle.dto.member.AddressDto;
import com.project.kostyle.entity.Member;

public interface AddressService {

    /*// 배송지 목록 조회
    List<AddressDTO> addressList();

    // 배송지 단일 조회
    AddressDTO addressDetail(Long ano);

    // 배송지 수정
    void updateAddress(AddressDTO dto);

    // 배송지 삭제
    void deleteAddress(Long ano);
     */


    //배송지 추가
    public Long createAddress(AddressDto addressDto, Member member);

    // 기본 배송지 설정
    //void updateDefault(Long ano);
    //AddressDto findDefaultAddress();
}
