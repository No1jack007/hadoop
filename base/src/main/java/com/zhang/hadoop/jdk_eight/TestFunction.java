package com.zhang.hadoop.jdk_eight;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author: zhang yufei
 * @create: 2019-10-25 16:17
 **/
public class TestFunction {

    public static void main(String args[]) {
        Hello hello = param -> param + "world!";
        System.out.println("test functibonal:" + hello.msg("hello,"));

        FunctionTest functionTest = (a, b) -> {
            String c=a + b;
            return c;
        };
    }

    Consumer c = new Consumer() {
        @Override
        public void accept(Object o) {
            System.out.println(o);
        }
    };

    public static void consumerTest() {
        Consumer f = System.out::println;
        Consumer f2 = n -> System.out.println(n + "-F2");

        //执行完F后再执行F2的Accept方法
        f.andThen(f2).accept("test");

        //连续执行F的Accept方法
        f.andThen(f).andThen(f).andThen(f).accept("test1");
    }

    /**
     * Function测试
     */
    public static void functionTest() {
        Function<Integer, Integer> f = s -> s++;
        Function<Integer, Integer> g = s -> s * 2;

        /**
         * 下面表示在执行F时，先执行G，并且执行F时使用G的输出当作输入。
         * 相当于以下代码：
         * Integer a = g.apply(1);
         * System.out.println(f.apply(a));
         */
        System.out.println(f.compose(g).apply(1));

        /**
         * 表示执行F的Apply后使用其返回的值当作输入再执行G的Apply；
         * 相当于以下代码
         * Integer a = f.apply(1);
         * System.out.println(g.apply(a));
         */
        System.out.println(f.andThen(g).apply(1));

        /**
         * identity方法会返回一个不进行任何处理的Function，即输出与输入值相等；
         */
        System.out.println(Function.identity().apply("a"));
    }

}
