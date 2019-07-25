package com.namimono.securitylogin.demo.business.auth.service;

import com.namimono.securitylogin.demo.business.auth.bean.User;
import com.namimono.securitylogin.demo.config.login.token.MailAuthenticationToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private AuthenticationManager authenticationManager;
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager,AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.authenticationManager = authenticationManager;
        this.authenticationManagerBuilder=authenticationManagerBuilder;
    }

    public void mailLogin(String username) throws Exception {
        MailAuthenticationToken mailAuthenticationToken = new MailAuthenticationToken(username);
        Authentication authentication = authenticationManager.authenticate(mailAuthenticationToken);

//        System.out.println(((User)authentication.getPrincipal()).getUsername());
//        authenticationManagerBuilder.inMemoryAuthentication().withUser(((User)authentication.getPrincipal()).getUsername()).roles("ADMIN");
        SecurityContextHolder.getContext().setAuthentication(authentication);



    }
}
