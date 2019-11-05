package com.zhang.hadoop.design_mode.adapter.object_adapter;

/**
 * @author: zhang yufei
 * @create: 2019-11-05 16:24
 * 基本思路和类的适配器模式相同，只是将Adapter类作修改，这次不继承Source类，
 * 而是持有Source类的实例，以达到解决兼容性的问题。看图：
 **/
public class AdapterTest {
    public static void main(String[] args) {
        Source source = new Source();
        Targetable target = new Wrapper(source);
        target.method1();
        target.method2();
    }
}
