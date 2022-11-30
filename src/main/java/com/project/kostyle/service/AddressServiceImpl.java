package com.project.kostyle.service;

import com.project.kostyle.dto.member.AddressDto;
import com.project.kostyle.entity.Address;
import com.project.kostyle.entity.Member;
import com.project.kostyle.repository.AddressRepository;
import com.project.kostyle.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService{

    private final AddressRepository addressRepository;

    //배송지 등록
    @Override
    public Long createAddress(AddressDto addressDto, Member member) {
        Long ano = addressRepository.save(Address.create(addressDto,member)).getAno();

        return ano;
    }

    //기본 배송지 설정
    /*@Override
    public void updateDefault(Long ano) {
        addressRepository.updateDefaultClear(SecurityUtil.getCurrentMemberId()); //기본 배송지 해제
        addressRepository.updateDefault(ano);
    }*/

}
