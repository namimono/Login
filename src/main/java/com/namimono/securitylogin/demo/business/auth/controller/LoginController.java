package com.namimono.securitylogin.demo.business.auth.controller;

import com.namimono.securitylogin.demo.business.auth.service.AuthService;
import com.namimono.securitylogin.demo.business.auth.service.MyUserDetailsService;
import com.namimono.securitylogin.demo.config.login.validate.mail.MailCodeProcessor;
import com.namimono.securitylogin.demo.config.login.validate.sms.SmsCodeProcessor;
import com.namimono.securitylogin.demo.config.result.MyResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(tags = "用户管理")
@RestController
@RequestMapping("auth")
@Slf4j
public class LoginController {

    @Autowired
    private SmsCodeProcessor smsCodeProcessor;

    @Autowired
    private MailCodeProcessor mailCodeProcessor;

    @Autowired
    private AuthService authService;
    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @ApiOperation(value = "用户登录",notes = "用户登录")
    @PostMapping("/login")
    public String login(String username,String password){
      log.info("login contoller");
      log.info("username"+username+"password:"+ password);
//      myUserDetailsService.loadUserByUsername(username);
        return "";
    }

    @ApiOperation(value = "获取短信验证码",notes = "获取短信验证码")
    @GetMapping("/login/smsValidate")
    public String smsValidate(HttpServletRequest request, HttpServletResponse response, @RequestParam String mobile){
        smsCodeProcessor.create(new ServletWebRequest(request,response));

        return "success";
    }


    @ApiOperation(value = "获取邮箱验证码",notes = "获取邮箱验证码")
    @GetMapping("/mailValidate")
    public String mailValidate(HttpServletRequest request, HttpServletResponse response){
        mailCodeProcessor.create(new ServletWebRequest(request,response));
        return "success";
    }

    @ApiOperation(value = "邮箱登陆",notes = "邮箱登录")
    @PostMapping("/mailLogin")
    public MyResult mailLogin(HttpServletRequest request, @RequestParam String code,@RequestParam String username){
        String validateCode = (String) request.getSession().getAttribute("validateCode");
//        if (code.equals(validateCode)){
//            System.out.println("验证码输入正确");
//            return MyResult.getSuccess();
//        }
        try {
            authService.mailLogin(username);
        } catch (Exception e) {
            e.printStackTrace();
            return MyResult.getFail();
        }

        return MyResult.getSuccess();
    }


}
