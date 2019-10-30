package com.zhang.hadoop.spring_IOC;

/**
 * @author: zhang yufei
 * @create: 2019-10-30 14:54
 **/
public class Main {
    public static void main(String[] args) {
        /******************** IOC控制反转和依赖注入 ***************************/
        // 利用容器，通过xml文件直接注入属性值，在Main类中只添加需要的
        // Chinese和American，当类和方法修改时，代码完全不用修改，只需要修改xml文件即可，彻底实现了解耦
        BeanFactory beanFactory = new BeanFactory();
        beanFactory.init("/config.xml");
        UserBean userBean = (UserBean) beanFactory.getBean("userBean");
        System.out.println("userName=" + userBean.getUserName());
        System.out.println("password=" + userBean.getPassword());
    }
}
