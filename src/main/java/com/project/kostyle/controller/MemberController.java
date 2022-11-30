package com.project.kostyle.controller;

import com.project.kostyle.dto.member.MemberDto;
import com.project.kostyle.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //회원가입
    @PostMapping("/create")
    public ResponseEntity<String> create(@Valid @RequestBody MemberDto memberDto){

        //검증 오류에 대한 예외처리
        try{
            memberService.create(memberDto);
            return new ResponseEntity<>("회원가입 성공", HttpStatus.OK);
        }catch (IllegalStateException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    //로그인
    @GetMapping("/login")
    public String login(){
        return "/loginForm";
    }

    @GetMapping("/login/error")
    public String loginError(Model model){
        model.addAttribute("loginErrorMsg", "이메일 또는 비밀번호를 확인해주세요");
        return "/loginForm";
    }

}
