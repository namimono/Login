package com.namimono.securitylogin.demo.config.login.provider;

//import com.namimono.business.service.MyUserDetailsService;
import com.namimono.securitylogin.demo.business.auth.bean.User;
import com.namimono.securitylogin.demo.business.auth.service.MyUserDetailsService;
import com.namimono.securitylogin.demo.config.login.token.MailAuthenticationToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.stereotype.Component;

@Slf4j
public class MailAuthenticationProvider implements AuthenticationProvider {


//    private static MyUserDetailsService myUserDetailService;
    private MyUserDetailsService myUserDetailsService;
    private AuthenticationManagerBuilder authenticationManagerBuilder;

    public MailAuthenticationProvider(MyUserDetailsService myUserDetailsService, AuthenticationManagerBuilder authenticationManagerBuilder) {
        this.myUserDetailsService = myUserDetailsService;
        this.authenticationManagerBuilder = authenticationManagerBuilder;
    }

    /**
     * 该方法用来验证authentication并返回验证成功的authentication
     * @param authentication
     * @return
     * @throws AuthenticationException
     */


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MailAuthenticationToken mailAuthenticationToken= (MailAuthenticationToken) authentication;
        UserDetails userDetails = myUserDetailsService.loadUserByUsername((String) authentication.getPrincipal());

        if (userDetails==null){
            log.warn("未获取到用户");
            throw new InternalAuthenticationServiceException("未获取到用户");
        }

//        log.info(userDetails.getUsername());
//        try {
////            authenticationManagerBuilder.inMemoryAuthentication().withUser(userDetails.getUsername()).roles(((User)userDetails).getRole());
//            authenticationManagerBuilder.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
//                    .withUser(userDetails.getUsername()).password("123123").roles(((User)userDetails).getRole());
//        } catch (Exception e) {
//            throw new InternalAuthenticationServiceException("无法验证");
//        }
//        创建验证完成的token
        /**
         *  userDetails.getAuthorities()方法会获取用户角色，用户角色
         */
        MailAuthenticationToken mailAuthenticationTokenResult = new MailAuthenticationToken(userDetails, userDetails.getAuthorities());
//        将原有未验证token中的信息设置到新的token中
        mailAuthenticationTokenResult.setDetails(mailAuthenticationToken.getDetails());
        return mailAuthenticationTokenResult;
    }
    /**
     * AuthenticationManager 是否支持该token类型。若支持会调用此provider类
     *
     * @param authentication
     *
     * @return <code>true</code> if the implementation can more closely evaluate the
     * <code>Authentication</code> class presented
     */
    @Override
    public boolean supports(Class<?> authentication) {
        return MailAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
