package com.basic.javabase.overrideTest;

/**
 * Created by lixiangcheng on 16/7/1.
 */
public class Son extends Parent {
    @Override
    public int hit() {
        System.out.println("son===========");
        return 1;
    }
}
