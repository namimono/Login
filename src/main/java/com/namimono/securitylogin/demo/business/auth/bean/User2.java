package com.namimono.securitylogin.demo.business.auth.bean;

import com.namimono.securitylogin.demo.config.utils.MyAnnotation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user2")
public class User2 {

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
    @MyAnnotation(name = "角色列表",isIgnore = true)
    private List<Role> roleList;



    public User2(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User2(String id, String username, String password, Integer roleId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.roleId = roleId;
    }

    public User2(String id) {
        this.id = id;
    }


}
