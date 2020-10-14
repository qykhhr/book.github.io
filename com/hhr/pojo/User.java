package com.hhr.pojo;

/**
 * @author qyk
 * @date 2020:09:19
 */
public class User {
    private Integer id;
    //role是区别于普通用户和管理员的
    //0表示普通用户（默认）
    ///1表示管理员
    //role属性只能获取，不能设置，构造器也不能改变
    //如需要将普通用户变成管理员，需要到数据库中将role属性进行修改即可
    //管理员不提供将普通用户变成管理员的功能
    private int role;
    private String username;
    private String password;
    private String email;

    public User() {
    }

    public User(Integer id, int role, String username, String password, String email) {
        this.id = id;
        this.role = 0;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getRole() {
        return role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
