package com.namimono.securitylogin.demo;

import com.namimono.securitylogin.demo.business.auth.bean.Role;
import com.namimono.securitylogin.demo.business.auth.bean.User;
import com.namimono.securitylogin.demo.business.auth.bean.User3;
import com.namimono.securitylogin.demo.business.auth.dao.UserDao;
import com.namimono.securitylogin.demo.business.testA.bean.Announcement;
import com.namimono.securitylogin.demo.business.testA.bean.Dept;
import com.namimono.securitylogin.demo.business.testA.dao.AnnouncementDao;
import com.namimono.securitylogin.demo.business.testA.dao.DeptDao;
import com.namimono.securitylogin.demo.config.utils.ReflectUtils;
import com.namimono.securitylogin.demo.config.utils.ReflectUtils3;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.StringUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Test2 {
    @Autowired
    AnnouncementDao announcementDao;
    @Test
    public void test1(){

        Announcement announcement = announcementDao.selectByPrimaryKey(1);
        System.out.println(announcement);
    }
}
