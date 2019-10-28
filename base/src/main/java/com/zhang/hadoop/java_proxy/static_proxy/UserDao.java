package com.zhang.hadoop.java_proxy.static_proxy;

/**
 * @author: zhang yufei
 * @create: 2019-10-28 16:28
 **/
/**
 * 接口实现
 * 目标对象
 */
public class UserDao implements IUserDao {
    public void save() {
        System.out.println("----已经保存数据!----");
    }
}