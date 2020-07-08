package com.jyotirmayadas.security6.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(Authentication authentication) {

        /*
        If any where we can't take the authentication from parameter the we can
        use the below method
         */
//        Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();

        System.out.println(authentication);
        return "Hello! " + authentication.getName(); //token

    }

}
