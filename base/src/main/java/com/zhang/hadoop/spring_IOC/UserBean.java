package com.zhang.hadoop.spring_IOC;

/**
 * @author: zhang yufei
 * @create: 2019-10-30 14:54
 **/
public class UserBean {
    private String userName;
    private String password;

    public String getPassword() {
        return password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
