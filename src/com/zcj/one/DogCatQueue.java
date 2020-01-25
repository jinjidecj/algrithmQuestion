package com.zcj.one;

import java.util.LinkedList;
import java.util.Queue;

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
