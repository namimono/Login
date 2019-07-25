package com.namimono.securitylogin.demo.business.auth.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User  {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "username" )
    private String username;

    @Column(name = "password" )
    private String password;

//    用户权限
    @Column(name = "role_id")
    private Integer roleId;




    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


}
