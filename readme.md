# 《IT 名企算法与数据结构题目最优解 --by左程云》笔记

## 一、栈和队列

### 1. 设计一个有getMin功能的栈

pop、push、getMin操作的时间复杂度为O(1)，getMin返回栈中的最小值

```java
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
```

这是一种方案，压入时会对比最小栈里的数据，比较节省空间。

**还有一种方案：**在压入时，如果数据大于最小栈的栈顶，则压入数据栈的时候，把最小栈的栈顶数据再压入一次，当出栈时，数据栈和最小栈同步弹出，这个方案压入时稍费空间，但是弹出操作稍省时间。

### 2.两个栈组成的队列

用两个栈实现队列，支持队列的基本操作（add,pull,peek）

**方案：**用两个堆栈来模拟队列，一个存放入栈数据，另一个在出队或者取队顶元素时，倒序保存入栈的数据，发生“压”的操作，要注意在取值和出队时的压操作

```java
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
```

### 3.如何仅用递归函数和栈操作逆序一个栈

```java
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
```

设计两个递归函数

+ getAndRemoveLastElement 把stack的栈底元素返回并移除
+ 逆序一个栈

### 4.猫狗队列

```java
class Pet{
    private String type;
    public Pet(String type){
        this.type = type;
    }
    public String getPetType(){
        return this.type;
    }
}

class Dog extends Pet{
    public Dog(){
        super("dog");
    }
}
class Cat extends Pet{
    public Cat(){
        super("cat");
    }
}

class PetEnterQueue{
    private Pet pet;
    private long count;
    public PetEnterQueue(Pet pet,long count){
        this.pet = pet;
        this.count = count;
    }
    public Pet getPet(){
        return this.pet;
    }
    public long getCount(){
        return this.count;
    }
    public String getEnterPetType(){
        return this.pet.getPetType();
    }
}

public class DogCatQueue {
    private Queue<PetEnterQueue> DogQ=new LinkedList<PetEnterQueue>();
    private Queue<PetEnterQueue> CatQ=new LinkedList<PetEnterQueue>();
    private long count = 0;
    public DogCatQueue(){}
    public void add(Pet pet)throws Exception{
        if(pet.getPetType().equals("dog")){
            this.DogQ.add(new PetEnterQueue(pet,this.count++));
        }else if(pet.getPetType().equals("cat")){
            this.CatQ.add(new PetEnterQueue(pet,count++));
        }else{
            throw new RuntimeException("err,not dog or cat!");
        }
    }
    public Pet pollAll(){
        if(!this.DogQ.isEmpty() && !this.CatQ.isEmpty()){
            if(this.DogQ.peek().getCount()>this.CatQ.peek().getCount()){
                return this.CatQ.poll().getPet();
            }else{
                return this.DogQ.poll().getPet();
            }
        }else if(!this.DogQ.isEmpty()){
            return this.DogQ.poll().getPet();
        }else if(!this.CatQ.isEmpty()){
            return this.CatQ.poll().getPet();
        }else{
            throw new RuntimeException("err,queue is empty!");
        }
    }
    public Dog pollDog(){
        if(!this.isDogQueueEmpty()){
            return (Dog)this.DogQ.poll().getPet();
        }else{
            throw new RuntimeException("dog queue is empty");
        }
    }
    public Cat pollCat(){
        if(!this.isCatQueueEmpty()){
            return (Cat)this.CatQ.poll().getPet();
        }else {
            throw new RuntimeException("cat queue is empty");
        }
    }

    public boolean isEmpty(){
        return this.DogQ.isEmpty() && this.CatQ.isEmpty();
    }
    public boolean isDogQueueEmpty(){
        return this.DogQ.isEmpty();
    }
    public boolean isCatQueueEmpty(){
        return this.CatQ.isEmpty();
    }
}
```

### 5.用一个栈实现另一个栈的排序

将栈中元素均为整形的栈按从大到小的顺序排序，只允许申请一个栈，新的变量，不可申请额外的数据结构。

```java
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
```

要排序的栈为stack，辅助栈help，stack执行pop操作，弹出的元素记为cur

+ cur< =help的栈顶元素，cur压入help
+ cur>help的栈顶元素，help的元素逐一弹出到stack，直到curl<=help的栈顶元素，再将cur压入help
+ 重复以上操作，直到stack为空，再把元素压回stack

### 6.用栈来求解汉诺塔问题

