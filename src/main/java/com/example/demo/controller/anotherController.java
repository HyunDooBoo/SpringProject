package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/another")
public class anotherController {

    @GetMapping("/explanation")
    public String explanation(){
        return "another/explanation";
    }
}
