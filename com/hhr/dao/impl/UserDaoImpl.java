package com.hhr.dao.impl;

import com.hhr.dao.UserDao;
import com.hhr.pojo.User;


/**
 * @author qyk
 * @date 2020:09:19
 */
public class UserDaoImpl extends BaseDao implements UserDao {

    @Override
    public User queryUserByUsername(String username) {
        String sql = "select id,username,password,email from user where username=?";
        return queryForOne(User.class,sql,username);
    }

    @Override
    public User queryUserByUsernameAndPassword(String username, String password) {
        String sql = "select id,username,password,email from user where username=? and password=?";
        return queryForOne(User.class,sql,username,password);
    }

    @Override
    public int saveUser(User user) {
        String sql = "insert into user(`username`,`password`,`email`) values (?,?,?)";
        return update(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }

    @Override
    public int queryRole(String username) {
        String sql = "select role from user where username=?";
        return (int) queryForSingleValue(sql,username);
    }
}
