package com.namimono.securitylogin.demo.business.auth.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table( name ="authority" )
public class Authority implements GrantedAuthority {
    /**
     *  权限id
     */
    @Id
    @Column(name="id")
    private String id;
    /**
     * 权限名
     */
    @Column(name="name")
    private String name;

    @Override
    public String getAuthority() {
        return name;
    }
}
