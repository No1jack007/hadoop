package com.zhang.hadoop.design_mode.composite;

/**
 * @author: zhang yufei
 * @create: 2019-11-05 17:03
 * 组合模式有时又叫部分-整体模式在处理类似树形结构的问题时比较方便
 **/
public class Tree {

    TreeNode root = null;

    public Tree(String name) {
        root = new TreeNode(name);
    }

    public static void main(String[] args) {
        Tree tree = new Tree("A");
        TreeNode nodeB = new TreeNode("B");
        TreeNode nodeC = new TreeNode("C");

        nodeB.add(nodeC);
        tree.root.add(nodeB);
        System.out.println("build the tree finished!");
    }

}
