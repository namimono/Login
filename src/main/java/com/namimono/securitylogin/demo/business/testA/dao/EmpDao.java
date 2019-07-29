package com.namimono.securitylogin.demo.business.testA.dao;

import com.namimono.securitylogin.demo.business.testA.bean.Emp;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by wuxiao on 2019/7/30 13:48.
 */
public interface EmpDao {
    @Select("select * from emp where dept_id=#{deptId}")
    @Results({
            @Result(property = "id",column = "id"),
            @Result(property = "name",column = "name"),
            @Result(property = "deptId",column = "dept_id")
    })
    public List<Emp> queryEmpByDeptId(String deptId);
}
