package com.lee.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 方法拦截器。
 * 获取方法执行时间
 * Created by leith on 2016/6/24.
 */
public class PrefInterceptor implements MethodInterceptor {

    Logger logger = Logger.getLogger(PrefInterceptor.class.getName());
    private static ConcurrentHashMap<String, MethodStats> methodStats = new ConcurrentHashMap<String, MethodStats>();
    private static long statLogFrequency = 10;//限定多少次汇总
    private static long methodWarningThreshold = 100;//超过多少ms警告

    public Object invoke(MethodInvocation method) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return method.proceed();
        } finally {
            updateStats(method.getMethod().getName(), (System.currentTimeMillis() - start));
        }
    }

    /**
     * @param methodName  方法
     * @param elapsedTime 方法执行的时间
     */
    private void updateStats(String methodName, long elapsedTime) {
        MethodStats stats = methodStats.get(methodName);
        if (stats == null) {
            stats = new MethodStats(methodName);
            methodStats.put(methodName, stats);
        }
        stats.count++;//汇总次数
        stats.totalTime += elapsedTime;//汇总时间
        if (elapsedTime > stats.maxTime) {
            stats.maxTime = elapsedTime;//记录当前最大时间消耗
        }

        if (elapsedTime > methodWarningThreshold) {//超过 警告
            logger.warn("method warning: " + methodName + "(), cnt = " + stats.count + ", lastTime = " + elapsedTime + ", maxTime = " + stats.maxTime);
        }

        if (stats.count % statLogFrequency == 0) {//来一次大的汇总
            long avgTime = stats.totalTime / stats.count;//运行多少次的平均时间
            long runningAvg = (stats.totalTime - stats.lastTotalTime) / statLogFrequency;//运行 statLogFrequency  次的平均时间
            logger.debug("method: " + methodName + "(), cnt = " + stats.count + ", lastTime = " + elapsedTime + ", avgTime = " + avgTime + ", runningAvg = " + runningAvg + ", maxTime = " + stats.maxTime);

            //reset the last total time
            stats.lastTotalTime = stats.totalTime;
        }
    }

    class MethodStats {
        public String methodName;
        public long count;//总的次数
        public long totalTime;//所有汇总时间
        public long lastTotalTime;//倒数第二次汇总时间
        public long maxTime;//运行中最大的耗时

        public MethodStats(String methodName) {
            this.methodName = methodName;
        }
    }

}
