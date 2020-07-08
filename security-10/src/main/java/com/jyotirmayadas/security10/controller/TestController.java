package com.jyotirmayadas.security10.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @PostMapping("/test")
//    @CrossOrigin("*") // not recommended
    public String test() {
        System.out.println(":(");
        return "Test!";
    }
}
