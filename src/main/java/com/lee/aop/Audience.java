package com.lee.aop;

/**
 * Created by lixiangcheng on 16/7/3.
 * 切入的类和方法
 */
public class Audience {
    public void takeSeats() {
        System.out.println("The audience is taking their seats.");
    }

    public void turnOffCellPhones() {
        System.out.println("The audience is turning off their cellphones");
    }

    public void applaud() {
        System.out.println("CLAP CLAP CLAP");
    }

    public void demandRefund() {
        System.out.println("Boo! We want money back");
    }
}
