package com.project.kostyle.controller;

import com.project.kostyle.dto.member.AddressDto;
import com.project.kostyle.entity.Member;
import com.project.kostyle.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

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

}
