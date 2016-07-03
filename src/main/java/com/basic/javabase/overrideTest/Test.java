package com.basic.javabase.overrideTest;

/**
 * Created by lixiangcheng on 16/7/1.
 */
public class Test {

    public static void main(String[] args){
        Parent parent = new Son();
        Parent parent1 = new Parent();
        parent.hit();
        parent1.hit();
    }
}
