package com.basic.javabase.singleton;

/**
 * Created by lixiangcheng on 16/7/7.
 */
public class LsingletonTest extends Thread {
    @Override
    public void run() {
        System.out.println(Lsingleton.getSingleton().hashCode());
    }

    public static void main(String[] agro) {
        LsingletonTest[] LsingletonTest = new LsingletonTest[10];
        for (int i = 0; i < LsingletonTest.length; i++) {
            LsingletonTest[i] = new LsingletonTest();
        }
        for (int i = 0; i < LsingletonTest.length; i++) {
            LsingletonTest[i].run();
        }
    }
}
