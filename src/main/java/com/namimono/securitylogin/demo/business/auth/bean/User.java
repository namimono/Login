package com.namimono.securitylogin.demo.business.auth.bean;

import com.namimono.securitylogin.demo.config.utils.MyAnnotation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {

    @Id
    @Column(name = "id")
    @MyAnnotation(alias = "用户id")
    private String id;

    @Column(name = "username" )
    @MyAnnotation(alias = "用户名")
    private String username;

    @Column(name = "password" )
    @MyAnnotation(alias = "密码")
    private String password;

    @Column(name = "role_id")
    @MyAnnotation(alias = "角色id")
    private Integer roleId;


    @Transient
    @MyAnnotation(alias = "角色名称")
    private String roleName;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String id) {
        this.id = id;
    }
}
