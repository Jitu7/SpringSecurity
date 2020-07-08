package com.jyotirmayadas.security10.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("/")
    public String main() {
        return "main.html";
    }

    /*@PostMapping("/test")
    @ResponseBody
    public String test() {
        return "Test!";
    }*/
}
