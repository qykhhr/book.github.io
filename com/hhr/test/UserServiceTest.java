package com.hhr.test;

import com.hhr.pojo.User;
import com.hhr.service.UserService;
import com.hhr.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author qyk
 * @date 2020:09:19
 */
public class UserServiceTest {
    private UserService userService = new UserServiceImpl();
    @Test
    public void login() {
        User user = userService.login("qykhhr", "qykhhr");
        if(user != null){
            System.out.println("登录成功");
        }else{
            System.out.println("登录失败");
        }
    }

    @Test
    public void regist() {
        int regist = userService.regist(new User(null, 1, "qykhhr", "qykhhr", "qykhhr@qq.com"));
        if(regist != 0){
            System.out.println("注册成功");
        }else{
            System.out.println("注册失败");
        }
    }

    @Test
    public void existsUsername() {
        boolean existsUsername = userService.existsUsername("hhr1");
        if(existsUsername){
            System.out.println("该用户名已被使用");
        }else{
            System.out.println("该用户名没有被使用");
        }
    }
}