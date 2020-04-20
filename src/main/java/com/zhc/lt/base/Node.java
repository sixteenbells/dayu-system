package com.zhc.lt.base;

/**
 * @author : sixteenbell
 * @version : 1.0
 * @date : 2020/2/2 2:55 PM
 * @description :
 */
public class Node {
    public int val;
    public Node left;
    public Node right;
    public Node next;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }
}
