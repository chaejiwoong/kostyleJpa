package com.project.kostyle.service;

import com.project.kostyle.dto.member.AddressDto;
import com.project.kostyle.entity.Member;

import java.util.List;

public interface AddressService {

    //배송지 추가
    public Long createAddress(AddressDto addressDto, Member member);

    //배송지 목록
    public List<AddressDto> findAll();

    //배송지 상세
    public AddressDto addressDetail(Long ano);

    //배송지 수정
    public Long update(AddressDto addressDto);

    //배송지 삭제
    public void delete(Long ano);


    // 기본 배송지 설정
    //void updateDefault(Long ano);
    //AddressDto findDefaultAddress();

    /*


    // 배송지 삭제
    void deleteAddress(Long ano);
     */



}
