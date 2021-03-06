package com.zhang.hadoop.java_proxy.Cglib_proxy;

/**
 * @author: zhang yufei
 * @create: 2019-10-28 16:42
 **/
/**
 * 测试类
 */
public class App {

    public static void main(String[] args) {
        //目标对象
        UserDao target = new UserDao();

        //代理对象
        UserDao proxy = (UserDao)new ProxyFactory(target).getProxyInstance();

        //执行代理对象的方法
        proxy.save();
    }
}