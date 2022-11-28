package com.project.kostyle.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public ResponseEntity<String> main(){
        return new ResponseEntity<>("main", HttpStatus.OK);
    }

    /*@GetMapping("/login/success")
    public ResponseEntity<String> loginSuccess(){
        return new ResponseEntity<>("로그인 성공", HttpStatus.OK);
    }*/

}
