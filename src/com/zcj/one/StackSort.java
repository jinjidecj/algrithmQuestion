package com.zcj.one;

import java.util.Stack;

//5.用一个栈实现另一个栈的排序
public class StackSort {
    private void stackSort(Stack<Integer> stack){
        Stack<Integer> help=new Stack<Integer>();
        while (!stack.isEmpty()){
            int cur = stack.pop();
            while (!help.isEmpty()&&help.peek()>cur){
                stack.push(help.pop());
            }
            help.push(cur);
        }
        while (!help.isEmpty()){
            stack.push(help.pop());
        }
    }

    public static void main(String[] args) {
        Stack<Integer> stack=new Stack<Integer>();
        int a[]=new int[]{1,2,5,3,8,4,80,21};
        for(int i=0;i<a.length;i++){
            stack.push(a[i]);
        }
        System.out.println(stack.toString());
        new StackSort().stackSort(stack);
        System.out.println(stack.toString());
    }
}
