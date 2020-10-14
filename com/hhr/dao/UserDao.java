package com.hhr.dao;

import com.hhr.pojo.User;

/**
 * @author qyk
 * @date 2020:09:19
 */
public interface UserDao {

    /**
     * 通过用户名查找用户
     * @param username
     * @return
     */
    public User queryUserByUsername(String username);

    /**
     * 通过用户名和密码查找用户
     * @param username
     * @param password
     * @return
     */
    public User queryUserByUsernameAndPassword(String username,String password);

    /**
     * 保存用户，true为保存成功，false为保存失败
     * @param user
     * @return
     */
    public int saveUser(User user);

    /**
     * 查找用户状态
     * @param username
     * @return
     */
    public int queryRole(String username);

}
