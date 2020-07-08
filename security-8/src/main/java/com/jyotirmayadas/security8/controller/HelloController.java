package com.jyotirmayadas.security8.controller;

import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
public class HelloController {

    //    @Async // spring will create the thread recommended
    @GetMapping("/hello")
    public String hello(/*Authentication authentication*/) {

        /*
        If any where we can't take the authentication from parameter the we can
        use the below method
         */
//        Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();

        Runnable r = () -> {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            System.out.println(authentication);
        };

        /*
        This will work even if we are not using SecurityContextHolder.MODE_INHERITABLETHREADLOCAL
        there are 2 options
         */
        // 1st opt
//        DelegatingSecurityContextRunnable dr = new DelegatingSecurityContextRunnable(r);

        ExecutorService executorService = Executors.newSingleThreadExecutor();
        DelegatingSecurityContextExecutorService delegatingSecurityContextExecutorService =
                new DelegatingSecurityContextExecutorService(executorService);
//        executorService.submit(dr);
        executorService.submit(r);
        return "Hello! ";

    }

}
