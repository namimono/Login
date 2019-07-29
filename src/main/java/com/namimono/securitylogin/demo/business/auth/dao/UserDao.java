package com.namimono.securitylogin.demo.business.auth.dao;


import com.namimono.securitylogin.demo.business.auth.bean.Authority;
import com.namimono.securitylogin.demo.business.auth.bean.Role;
import com.namimono.securitylogin.demo.business.auth.bean.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserDao extends Mapper<User> {

    @Select("select * from user where username=#{username}")
    public User getUserByName(String username);

    @Select("select role.* from role left join user on role.id = user.role_id where user.id=#{id}")
    public List<Role> getRoleListById(String id);

    @Select("<script>SELECT authority.* FROM role_authority as ra LEFT JOIN authority\n" +
            "ON ra.auth_id=authority.id WHERE ra.role_id in " +
            "<foreach collection=\"roleList\" item=\"item\" open=\"(\" sparator=\",\" close=\")\">" +
            "#{item.id}" +
            "</foreach>" +
            "</script>")
    public List<Authority> getRoleAuthList(@Param("roleList")List<Role> roleList);

    @Select("select role.name as roleName,u.* from user u left join role on u.role_id = role.id where username  = #{username}")
    public User getUserWithRoleByName(String username);
}
