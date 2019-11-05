package com.zhang.hadoop.design_mode.iterator;

/**
 * @author: zhang yufei
 * @create: 2019-11-05 17:48
 **/
public class MyCollection implements Collection {

    public String string[] = {"A","B","C","D","E"};
    @Override
    public Iterator iterator() {
        return new MyIterator(this);
    }

    @Override
    public Object get(int i) {
        return string[i];
    }

    @Override
    public int size() {
        return string.length;
    }
}