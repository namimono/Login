package com.namimono.securitylogin.demo.business.auth.bean;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class MyUserDetail implements UserDetails {
    /**
     *
     * 用户id
     */
    private String id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    //    用户权限
    /**
     * 角色
     */
    private List<Role> roleList;

    /**
     * 权限
     */
    private List<Authority> authorityList;



    public MyUserDetail(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public MyUserDetail(String username, String password,List<Role> roles,List<Authority> authorityList) {
        this.username = username;
        this.password = password;
        this.roleList=roles;
        this.authorityList=authorityList;
    }


    //    获取用户权限

    /**
     *
     * @return 返回继承GrantedAuthority类的集合
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> lists = new ArrayList<>();
        lists.addAll(roleList);
        lists.addAll(authorityList);
        return lists;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
