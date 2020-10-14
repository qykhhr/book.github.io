package com.hhr.service;

import com.hhr.pojo.User;

/**
 * @author qyk
 * @date 2020:09:19
 */
public interface UserService {
    /**
     * 注册用户
     * @param
     */
    public User login(String username,String password);

    /**
     * 注册用户
     * @param user
     */
    public int regist(User user);

    /**
     * 判断用户名是否存在，存在即不可用；不存在即可用
     * @param username
     * @return
     */
    public boolean existsUsername(String username);

    /**
     * 通过username属性查找到用户，再通过role属性查找用户是否是管理员
     * @return
     */
    public boolean isManager(String username);
}
