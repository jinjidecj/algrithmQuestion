package com.zcj.one;

import java.util.Stack;

//2.两个栈组成的队列
public class StackAsQueue {
    private Stack<Integer> stackIn = new Stack<Integer>();
    private Stack<Integer> stackOut = new Stack<Integer>();
    private void add(int value){
        stackIn.push(value);
    }
    private int peek()throws Exception{
        if(stackOut.isEmpty()&&stackIn.isEmpty()){
            throw new RuntimeException("Queue is empty");
        }else if(stackOut.isEmpty()){
            reviseStack();
        }
        return stackOut.peek();
    }
    private int poll(){
        if(stackOut.isEmpty()&&stackIn.isEmpty()){
            throw new RuntimeException("Queue is empty");
        }else if(stackOut.isEmpty()){
            reviseStack();
        }
        return stackOut.pop();
    }
    private void reviseStack(){
        while (!stackIn.isEmpty()){
            stackOut.push(stackIn.pop());
        }
    }

    public static void main(String[] args) {
        StackAsQueue stackAsQueue = new StackAsQueue();
        stackAsQueue.add(1);
        stackAsQueue.add(2);
        System.out.println(stackAsQueue.poll());
        stackAsQueue.add(3);
        System.out.println(stackAsQueue.poll());
        stackAsQueue.add(4);
        System.out.println(stackAsQueue.poll());
        stackAsQueue.add(5);
        System.out.println(stackAsQueue.poll());
        System.out.println(stackAsQueue.poll());
    }

}
