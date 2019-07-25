package com.namimono.securitylogin.demo.business.auth.service;

import com.namimono.securitylogin.demo.business.auth.bean.Authority;
import com.namimono.securitylogin.demo.business.auth.bean.MyUserDetail;
import com.namimono.securitylogin.demo.business.auth.bean.Role;
import com.namimono.securitylogin.demo.business.auth.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.AntPathMatcher;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Slf4j
public class RbacService {
//使用AntPathMatcher适配url，由于restful风格的url会出现aa/*的情况。
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    public boolean hasPermission(HttpServletRequest request, Authentication authentication) {
        Object principal = authentication.getPrincipal();
        boolean hasPermission = false;
//        判断传入的authentication是否属于UserDetails，如果是表明已经授权。
        if (principal instanceof UserDetails) {
//          获取当前已授权的authentication的权限列表
            List<Authority> authorityList = ((MyUserDetail) principal).getAuthorityList();
//            遍历列表，对比当前url是否存在于权限列表，若存在，返回true，退出循环。
            for (Authority authority : authorityList) {
                log.info("数据库:"+authority.getName()+"--- 当前url:"+request.getRequestURI());
                if (antPathMatcher.match(authority.getName(), request.getRequestURI())) {
                    hasPermission = true;
                    break;
                }
            }


        }
        return hasPermission;

    }
}
