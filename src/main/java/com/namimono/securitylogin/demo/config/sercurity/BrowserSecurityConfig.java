package com.namimono.securitylogin.demo.config.sercurity;

import com.namimono.securitylogin.demo.business.auth.service.MyUserDetailsService;
import com.namimono.securitylogin.demo.config.login.provider.MailAuthenticationProvider;
import com.namimono.securitylogin.demo.config.properties.LoginProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
@EnableConfigurationProperties(LoginProperties.class)
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {
//    只要容器中有PasswordEncoder，每次UserDetailsService.loadUserByUsername()登陆的时候都会调用PasswordEncoder.matches()方法将密码解析。
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//
//        return new BCryptPasswordEncoder();
//    }

//    注入AuthenticationManager

    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private AuthenticationManagerBuilder authenticationManagerBuilder;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        添加自定义的provider
        http.authenticationProvider(new MailAuthenticationProvider(myUserDetailsService,authenticationManagerBuilder));
        http.formLogin()
                .loginProcessingUrl("/auth/login")
//                .loginProcessingUrl("/auth/mailLogin")
//                .loginProcessingUrl("/auth/mailValidate")
//                .loginProcessingUrl("/auth/smsLogin")
//                .loginProcessingUrl("/auth/smsValidate")
                .and()
                .csrf().disable()
                .authorizeRequests()
//                .anyRequest()
//                .authenticated()

//                必须放行所有静态资源，否则swagger无法正常访问
                .antMatchers(
                        HttpMethod.GET,
                        "/",
                        "/*.html",
                        "/favicon.ico",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js").permitAll()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/auth/login").permitAll()
                .antMatchers("/auth/mailLogin").permitAll()
                .antMatchers("/auth/mailValidate").permitAll()
                .antMatchers("/auth/smsLogin").permitAll()
                .antMatchers("/auth/smsValidate").permitAll()

                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/authUser/login").permitAll()
                .antMatchers("/auth/**").permitAll()
                .antMatchers("/static/**").permitAll()  //过滤 允许
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/v2/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/api-docs/**").permitAll()
//                设置只需要登陆即可访问的url
                .antMatchers("/hello/getCWithoutAuthority").authenticated()
//                除了上述请求，拦截所有请求
                .anyRequest().access("@rbacService.hasPermission(request,authentication)")
//                .anyRequest().fullyAuthenticated()
                .and()
                .exceptionHandling()
//                添加验证失败执行逻辑
                .authenticationEntryPoint(new RestAuthenticationEntryPoint())
                .accessDeniedHandler(new RestAuthenticationAccessDeniedHandler());


    }
}
