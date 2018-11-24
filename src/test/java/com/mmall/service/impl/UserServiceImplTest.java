package com.mmall.service.impl;

import com.mmall.common.BaseTest;
import com.mmall.dao.UserMapper;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.*;

/**
 * @author gexiao
 * @date 2018/11/23 下午 03:41
 */


public class UserServiceImplTest  extends BaseTest {

    @Autowired
    private UserMapper userMapper;
    @Test
    public void login() throws Exception {
        int admin = userMapper.checkUsername("admin");
        Assert.assertNotEquals(0,admin);
    }

}