package com.namimono.securitylogin.demo.business.auth.service;

import com.namimono.securitylogin.demo.business.auth.bean.Authority;
import com.namimono.securitylogin.demo.business.auth.bean.MyUserDetail;
import com.namimono.securitylogin.demo.business.auth.bean.Role;
import com.namimono.securitylogin.demo.business.auth.bean.User;
import com.namimono.securitylogin.demo.business.auth.dao.UserDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Primary
public class MyUserDetailsService implements UserDetailsService {

    /**
     * MyUserDetailsService主要用于查询用户的所有信息，并返回UserDetails
     */
    @Autowired
    private UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        查询用户信息
        User user = userDao.getUserByName(username);
//        log.info(user.toString());
        if (user==null){

            throw new UsernameNotFoundException("用户不存在");

        }else {
//            查询用户角色
            List<Role> roleList = userDao.getRoleListById(user.getId());
            System.out.println(roleList);
            if (roleList.size()>0){
                List<Authority> roleAuthList = userDao.getRoleAuthList(roleList);
                return new MyUserDetail(user.getUsername(), user.getPassword(),roleList,roleAuthList);
            }

            return new MyUserDetail(user.getUsername(),user.getPassword());
        }
    }
}
