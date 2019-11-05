package com.zhang.hadoop.design_mode.iterator;


/**
 * @author: zhang yufei
 * @create: 2019-11-05 17:47
 **/
public interface Collection {

    public Iterator iterator();

    /*取得集合元素*/
    public Object get(int i);

    /*取得集合大小*/
    public int size();
}
