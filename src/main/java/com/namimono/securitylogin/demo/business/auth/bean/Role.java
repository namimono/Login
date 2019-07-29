package com.namimono.securitylogin.demo.business.auth.bean;

import com.namimono.securitylogin.demo.config.utils.MyAnnotation;
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
@Table( name ="role" )
public class Role implements GrantedAuthority {
    /**
     * 角色名
     */
    @Id
    @Column(name = "id" )
    @MyAnnotation(alias = "角色id")
    private String id;

    @Column(name = "name" )
    @MyAnnotation(alias = "角色名称")
    private String name;
    @Override
    public String getAuthority() {
        return name;
    }
}
