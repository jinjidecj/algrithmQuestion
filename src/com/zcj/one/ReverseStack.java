package com.zcj.one;

import java.util.Arrays;
import java.util.Stack;

//3.如何仅用递归函数和栈操作逆序一个栈
public class ReverseStack {
    private void reverseStack(Stack<Integer> stack){
        if (stack.isEmpty()) {
            return;
        }
        int i = getAndRemoveLastElement(stack);
        reverseStack(stack);
        stack.push(i);

    }
    private int getAndRemoveLastElement(Stack<Integer> stack){
        int result = stack.pop();
        if(stack.isEmpty()){
            return result;
        }else{
            int last = getAndRemoveLastElement(stack);
            stack.push(result);
            return last;
        }

    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();
        ReverseStack reverseStack = new ReverseStack();
        for(int i=0;i<5;i++)
            stack.push(i+1);
        System.out.println(stack.toString());
        try {
            reverseStack.reverseStack(stack);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(stack.toString());
    }
}
