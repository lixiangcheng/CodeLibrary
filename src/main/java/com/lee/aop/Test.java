package com.lee.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by lixiangcheng on 16/7/3.
 */
public class Test {
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("springmvc.xml");

        PerformerImpl performer = (PerformerImpl) ctx.getBean("lee");
        performer.perform();
    }

}
