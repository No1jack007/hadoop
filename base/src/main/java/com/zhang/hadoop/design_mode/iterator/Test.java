package com.zhang.hadoop.design_mode.iterator;

/**
 * @author: zhang yufei
 * @create: 2019-11-05 17:50
 **/
public class Test {

    public static void main(String[] args) {
        Collection collection = new MyCollection();
        Iterator it = collection.iterator();

        while(it.hasNext()){
            System.out.println(it.next());
        }
    }

}
