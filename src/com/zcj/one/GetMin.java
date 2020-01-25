package com.zcj.one;

import java.util.Stack;
//1. 设计一个有getMin功能的栈
public class GetMin {
    private Stack<Integer> stack = new Stack<Integer>();
    private Stack<Integer> stackMin = new Stack<Integer>();

    private void push(int in){
        stack.push(in);
        if(stackMin.isEmpty()){
            stackMin.push(in);
        }else if(in<=stackMin.peek()){
            stackMin.push(in);
        }
    }
    private int pop()throws Exception{
        if(stack.isEmpty()){
            throw new Exception("stack is empty");
        }
        int value = stack.pop();
        if(value==stackMin.peek()){
            stackMin.pop();
        }
        return value;
    }
    private int getMin()throws Exception{
        if(stackMin.isEmpty()){
            throw new Exception("stack is empty");
        }
        return stackMin.peek();
    }

    public static void main(String[] args) {
        try {
            GetMin gitm = new GetMin();
            gitm.push(3);
            gitm.push(4);
            gitm.push(6);
            gitm.push(5);
            gitm.push(8);
            gitm.push(89);
            gitm.push(98);
            gitm.push(2);
            System.out.println(gitm.getMin());
            System.out.println(gitm.pop());
            System.out.println(gitm.getMin());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
