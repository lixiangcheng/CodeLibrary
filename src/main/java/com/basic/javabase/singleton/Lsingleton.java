package com.basic.javabase.singleton;

/**
 * Created by lixiangcheng on 16/7/6.
 * 单例模式之 懒汉式
 */
public class Lsingleton {
    private Lsingleton() {

    }

    private static Lsingleton singleton = null;

    public synchronized static Lsingleton getSingleton() {
        if (singleton == null) {
            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            return new Lsingleton();
            singleton = new Lsingleton();
        }
        return singleton;
    }


}
