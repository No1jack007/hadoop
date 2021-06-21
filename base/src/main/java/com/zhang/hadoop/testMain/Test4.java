package com.zhang.hadoop.testMain;

import org.springframework.beans.BeanUtils;

import java.util.Arrays;
import java.util.List;

/**
 * @author ：zhangyufei.zhang
 * @date ：Created in 2021-06-21 10:53
 * @description：
 */
public class Test4 {

    public static void main(String[] args) {
        User user1=new User();
        user1.setUserId(1);
        user1.setList(Arrays.asList(1,2,3,4,5));
        System.out.println(user1);
        User user2=user1;
        user2.setList(user2.getList().subList(0,1));
        System.out.println(user2);
        User user3=new User();
        BeanUtils.copyProperties(user1,user3);

    }

    public static class User{

        private Integer userId;

        private List<Integer> list;

        public Integer getUserId() {
            return userId;
        }

        public void setUserId(Integer userId) {
            this.userId = userId;
        }

        public List<Integer> getList() {
            return list;
        }

        public void setList(List<Integer> list) {
            this.list = list;
        }

        @Override
        public String toString() {
            return "User{" +
                    "userId=" + userId +
                    ", list=" + list +
                    '}';
        }
    }
}
