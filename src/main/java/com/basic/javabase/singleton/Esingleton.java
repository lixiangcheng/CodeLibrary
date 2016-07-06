package com.basic.javabase.singleton;

/**
 * Created by lixiangcheng on 16/7/6.
 * 饿汉式单例是指在方法调用前，实例就已经创建好了
 */
public class Esingleton {
    private Esingleton(){

    }

    private static Esingleton Esingleton = new Esingleton();

    public static Esingleton getEsingleton(){
        return Esingleton;
    }
}
