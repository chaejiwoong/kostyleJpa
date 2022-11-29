package com.project.kostyle.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/main")
    public ResponseEntity<String> main(){
        return new ResponseEntity<>("mainPage", HttpStatus.OK);
    }

}
