package com.hhr.test;

import com.hhr.dao.UserDao;
import com.hhr.dao.impl.UserDaoImpl;
import com.hhr.pojo.User;
import org.junit.Test;


/**
 * @author qyk
 * @date 2020:09:19
 */
public class UserDaoTest {
    private static UserDao userDao = new UserDaoImpl();
    @Test
    public void queryUserByUsername() {
        System.out.println(userDao.queryUserByUsername("hhr"));
    }

    @Test
    public void queryUserByUsernameAndPassword() {
        System.out.println(userDao.queryUserByUsernameAndPassword("abc", "abc"));

    }

    @Test
    public void saveUser() {
        int i = userDao.saveUser(new User(1, 1, "abcdefG", "abcdefG", "abcdefG@qq.com"));
        if(i <= 0){
            System.out.println("保存失败");
        }else{
            System.out.println("保存成功");
        }
    }
}