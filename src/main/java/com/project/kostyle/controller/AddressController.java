package com.project.kostyle.controller;

import com.project.kostyle.dto.member.AddressDto;
import com.project.kostyle.entity.Member;
import com.project.kostyle.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member/address")
public class AddressController {

    private final AddressService addressService;

    //배송지 등록
    @GetMapping("/create")
    public ResponseEntity<String> create(){
        return new ResponseEntity<>("배송지 등록 폼", HttpStatus.OK);
    }
    @PostMapping("/create")
    public ResponseEntity<Long> create(@RequestBody AddressDto addressDto, HttpSession session){
        Member member = (Member) session.getAttribute("member");

        return new ResponseEntity<>(addressService.createAddress(addressDto, member),
                HttpStatus.OK);

    }

    //배송지 목록
    @GetMapping("/ajax")
    public ResponseEntity<List<AddressDto>> addressList(){
        return new ResponseEntity<List<AddressDto>>(addressService.findAll(), HttpStatus.OK);
    }

    //배송지 상세
    @GetMapping("/ajax/{ano}")
    public ResponseEntity<AddressDto> addressDetail(@PathVariable Long ano){
        return new ResponseEntity<AddressDto>(addressService.addressDetail(ano), HttpStatus.OK);
    }

    //배송지 수정
    @PatchMapping("/update/{ano}")
    public ResponseEntity<String> update(@RequestBody AddressDto addressDto,
                                                @PathVariable Long ano){

        addressDto.setAno(ano);
        addressService.update(addressDto);

        return new ResponseEntity<String>("배송지 수정 완료", HttpStatus.OK);
    }


    //배송지 삭제
    @DeleteMapping("/delete/{ano}")
    public ResponseEntity<String> delete(@PathVariable Long ano){
        addressService.delete(ano);
        return new ResponseEntity<String>("배송지 삭제 완료", HttpStatus.OK);
    }

    //기본 배송지 설정

    //기본 배송지 해제

}
