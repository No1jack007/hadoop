package com.zhang.hadoop.java_proxy.Cglib_proxy;

/**
 * @author: zhang yufei
 * @create: 2019-10-28 16:40
 **/
/**
 * 目标对象,没有实现任何接口
 */
public class UserDao {

    public void save() {
        System.out.println("----已经保存数据!----");
    }

}
