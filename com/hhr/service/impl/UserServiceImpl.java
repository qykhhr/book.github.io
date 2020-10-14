package com.hhr.service.impl;

import com.hhr.dao.UserDao;
import com.hhr.dao.impl.BaseDao;
import com.hhr.dao.impl.UserDaoImpl;
import com.hhr.pojo.User;
import com.hhr.service.UserService;

/**
 * @author qyk
 * @date 2020:09:19
 */
public class UserServiceImpl extends BaseDao implements UserService {
    UserDao userDao = new UserDaoImpl();
    @Override
    public User login(String username,String password) {
        return userDao.queryUserByUsernameAndPassword(username,password);
    }

    @Override
    public int regist(User user) {
        return userDao.saveUser(user);
    }

    @Override
    public boolean existsUsername(String username) {
        User user = userDao.queryUserByUsername(username);
        if(user == null){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public boolean isManager(String username) {
        int role = userDao.queryRole(username);
        if(role == 0){
            return false;
        }else{
            return true;
        }
    }
}
