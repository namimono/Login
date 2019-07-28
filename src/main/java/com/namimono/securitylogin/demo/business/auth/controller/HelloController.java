package com.namimono.securitylogin.demo.business.auth.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("hello")
public class HelloController {

//    @PreAuthorize("hasAuthority('getHello')")
//    @GetMapping("/getHello")
//    public String getHello(HttpServletRequest request){
//        System.out.println(request.getRequestURI());
//        return "Hello you are login";
//    }
//
////    @Secured("ADMIN")
////    该方法不起作用
//    @PreAuthorize("hasAuthority('getRoleHello')")
//    @GetMapping("/getRoleHello")
//    public String getRoleHello(){
//
//        return "Hello you are login";
//    }
//
//    @GetMapping("/getA")
//    @PreAuthorize("hasAuthority('getA')")
//    public String getA(){
//
//        return "get A";
//    }
//    @GetMapping("/getB")
//    @PreAuthorize("hasAuthority('getB')")
//    public String getB(){
//
//        return "get B";
//    }
    @GetMapping("/getHello")
    public String getHello(HttpServletRequest request){
        System.out.println(request.getRequestURI());
        return "Hello you are login";
    }

    @GetMapping("/getRoleHello")
    public String getRoleHello(){

        return "Hello you are login";
    }

    @GetMapping("/getA")
    public String getA(){

        return "get A";
    }
    @GetMapping("/getB")
    public String getB(){

        return "get B";
    }
    @GetMapping("/getCWithoutAuthority")
    public String getC(){

        return "get B";
    }
}
