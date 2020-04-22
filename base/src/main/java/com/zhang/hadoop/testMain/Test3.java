package com.zhang.hadoop.testMain;

public class Test3 {

    public static void main(String args[]) {
        int arr1[] = {5, 2, 1, 4, 3};
        for (int i = 0; i < arr1.length; i++) {
            System.out.println("外");
            for (int j = i + 1; j < arr1.length; j++) {
                System.out.println("内");
                if (arr1[i] > arr1[j]) {
                    int a = arr1[i];
                    arr1[i] = arr1[j];
                    arr1[j] = a;
                }
            }
            for(int a:arr1){
                System.out.print(a+"\t");

            }
            System.out.println();
        }
        for (int k = 0; k < arr1.length; k++) {
            System.out.println(arr1[k]);
        }
    }
}
