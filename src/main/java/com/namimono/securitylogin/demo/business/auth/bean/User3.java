package com.namimono.securitylogin.demo.business.auth.bean;

import com.namimono.securitylogin.demo.config.utils.MyAnnotation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * Created by wuxiao on 2019/7/31 9:10.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User3 extends User2{

    @Transient
    @MyAnnotation(name = "角色",isAdd = true)
    private String addInfo;

    @Transient
    @MyAnnotation(name = "角色" ,isDelete = true)
    private String deleteInfo;


    @Transient
    @MyAnnotation(name = "角色" ,isUpdate = true)
    private List<Role> updateList;

    @Transient
    @MyAnnotation(name = "角色列表",isIgnore = true)
    private List<Role> roleList;

    public User3(String id, String username, String password, Integer roleId, List<Role> roleList, String addInfo, String deleteInfo, List<Role> updateList) {
        super(id, username, password, roleId);
        this.addInfo = addInfo;
        this.deleteInfo = deleteInfo;
        this.updateList = updateList;
        this.roleList=roleList;
    }
}
