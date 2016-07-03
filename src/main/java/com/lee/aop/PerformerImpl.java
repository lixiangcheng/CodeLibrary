package com.lee.aop;

/**
 * Created by lixiangcheng on 16/7/3.
 */
public class PerformerImpl implements Performer {
    private String song;
    private int age;
    private Instrument instrument;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    public Instrument getInstrument() {
        return instrument;
    }

    public void setInstrument(Instrument instrument) {
        this.instrument = instrument;
    }

    public PerformerImpl() {
    }

    public PerformerImpl(String song, int age, Instrument instrument) {
        this.song = song;
        this.age = age;
        this.instrument = instrument;
    }

    public void perform() {
        System.out.println("PerformerImpl age:" + age);
        System.out.print("Playing " + song + ":");
        instrument.play();
    }
}
