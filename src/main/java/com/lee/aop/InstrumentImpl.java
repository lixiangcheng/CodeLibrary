package com.lee.aop;

/**
 * Created by lixiangcheng on 16/7/3.
 */
public class InstrumentImpl implements Instrument {
    public InstrumentImpl() {
    }

    public void play() {
        System.out.println("TOOT TOOT TOOT");
    }
}
