package com.namimono.securitylogin.demo;

import com.namimono.securitylogin.demo.business.auth.bean.Role;
import com.namimono.securitylogin.demo.business.auth.bean.User;
import com.namimono.securitylogin.demo.business.auth.bean.User2;
import com.namimono.securitylogin.demo.business.auth.bean.User3;
import com.namimono.securitylogin.demo.business.auth.dao.UserDao;
import com.namimono.securitylogin.demo.business.testA.bean.Dept;
import com.namimono.securitylogin.demo.business.testA.dao.DeptDao;
import com.namimono.securitylogin.demo.config.utils.ReflectUtils;
import com.namimono.securitylogin.demo.config.utils.ReflectUtils2;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class DemoApplicationTests {
    @Autowired
    UserDao userDao;

    @Autowired
    DeptDao deptDao;
    @Test
    public void contextLoads() {
        String s = UUID.randomUUID().toString().replaceAll("-", "");
        System.out.println(s);
    }

    @Test
    public void mapperTest() {
        User user = userDao.selectByPrimaryKey(new User(String.valueOf(1)));
        System.out.println(user);
    }

    @Test
    public void compareTest(){

        User user = new User("1","张三", "aaa",12,"ROLE_ADMIN");
        User user1 = userDao.getUserWithRoleByName("mea");

        System.out.println(user1);
        String s = ReflectUtils.packageModifyContent(user, user1);
        log.info(s);
    }
    @Test
    public void insertUserTest(){

        User user = new User("3","张三", "aaa",12,"ROLE_ADMIN");
        userDao.insert(user);
    }
    @Test
    public void testCollection(){
        Dept dept = deptDao.getPersonById("1");
        System.out.println(dept);
    }
    @Test
    public void ReflectTest2() throws NoSuchFieldException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {

    }

    @Test
    public void testSplit(){
        String deleteInfo = "成人票,儿童票";
        String[] splitA = StringUtils.delimitedListToStringArray(deleteInfo, ",");
//        for (int i = 0;i<strings.length;i++){
//            String[] string2 = StringUtils.delimitedListToStringArray(s, ";");
//
//        }
        for (String s: splitA)
        {
                System.out.println(s);
        }
    }
    @Test
    public void testSSSSS() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {

    }

    @Test
    public void Genericity() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        List<Role> roleList=new ArrayList<>();
        List<Role> roleList2=new ArrayList<>();

        Role role=new Role("1","ROLE_ADMIN");
        Role role2=new Role("2","ROLE_VIP");
        roleList.add(role);
        roleList.add(role2);
        User3 user = new User3("3","张三", "aaa",12,roleList,"部门经理，超级管理员","管理员",roleList);

        Role role3=new Role("1","AAAAAAAAAA");
        Role role4=new Role("2","BBBBBBBBBB");
        roleList2.add(role3);
        roleList2.add(role4);
        User3 user2 = new User3("3","张三", "bbb",12,roleList2,"部门经理，超级管理员","管理员",roleList2);

        String s = ReflectUtils2.packageModifyContent(user, user2);
        System.out.println(s);
    }
}
