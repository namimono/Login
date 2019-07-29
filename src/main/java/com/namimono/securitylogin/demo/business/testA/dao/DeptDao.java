package com.namimono.securitylogin.demo.business.testA.dao;

import com.namimono.securitylogin.demo.business.testA.bean.Dept;
import com.namimono.securitylogin.demo.business.testA.bean.Emp;
import org.apache.ibatis.annotations.*;

/**
 * Created by wuxiao on 2019/7/30 13:48.
 */
public interface DeptDao {

    @Select("select * from dept where dept.id=#{id}")
    @Results({
            @Result(property = "id" ,column = "id"),
            @Result(property = "name" ,column = "name"),
//            此处column传入 一的一方 id，用于查询语句
            @Result(property = "empList", column = "id",
                    many = @Many(select = "com.namimono.securitylogin.demo.business.testA.dao.EmpDao.queryEmpByDeptId"))
    })
    public Dept getPersonById(String id);
}
