package com.zhc.lt.base;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2019/12/9 3:51 PM
 * @description :
 */
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode(int x) {
        val = x;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }

    public static TreeNode buildTree(Integer[] arrays) {
        return recursiveBuild(0, arrays);
    }

    private static TreeNode recursiveBuild(int num, Integer[] arrays) {
        if (num >= arrays.length || arrays[num] == null) {
            return null;
        }
        TreeNode treeNode = new TreeNode(arrays[num]);
        treeNode.left = recursiveBuild(2 * num + 1, arrays);
        treeNode.right = recursiveBuild((num + 1) * 2, arrays);
        return treeNode;
    }
}
