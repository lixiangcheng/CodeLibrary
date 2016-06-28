package com.lee.interceptor;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.log4j.Logger;

import java.util.concurrent.ConcurrentHashMap;

/**
 * ������������
 * ��ȡ����ִ��ʱ��
 * Created by leith on 2016/6/24.
 */
public class PrefInterceptor implements MethodInterceptor {

    Logger logger = Logger.getLogger(PrefInterceptor.class.getName());
    private static ConcurrentHashMap<String, MethodStats> methodStats = new ConcurrentHashMap<String, MethodStats>();
    private static long statLogFrequency = 10;//�޶����ٴλ���
    private static long methodWarningThreshold = 100;//��������ms����

    public Object invoke(MethodInvocation method) throws Throwable {
        long start = System.currentTimeMillis();
        try {
            return method.proceed();
        } finally {
            updateStats(method.getMethod().getName(), (System.currentTimeMillis() - start));
        }
    }

    /**
     * @param methodName  ����
     * @param elapsedTime ����ִ�е�ʱ��
     */
    private void updateStats(String methodName, long elapsedTime) {
        MethodStats stats = methodStats.get(methodName);
        if (stats == null) {
            stats = new MethodStats(methodName);
            methodStats.put(methodName, stats);
        }
        stats.count++;//���ܴ���
        stats.totalTime += elapsedTime;//����ʱ��
        if (elapsedTime > stats.maxTime) {
            stats.maxTime = elapsedTime;//��¼��ǰ���ʱ������
        }

        if (elapsedTime > methodWarningThreshold) {//���� ����
            logger.warn("method warning: " + methodName + "(), cnt = " + stats.count + ", lastTime = " + elapsedTime + ", maxTime = " + stats.maxTime);
        }

        if (stats.count % statLogFrequency == 0) {//��һ�δ�Ļ���
            long avgTime = stats.totalTime / stats.count;//���ж��ٴε�ƽ��ʱ��
            long runningAvg = (stats.totalTime - stats.lastTotalTime) / statLogFrequency;//���� statLogFrequency  �ε�ƽ��ʱ��
            logger.debug("method: " + methodName + "(), cnt = " + stats.count + ", lastTime = " + elapsedTime + ", avgTime = " + avgTime + ", runningAvg = " + runningAvg + ", maxTime = " + stats.maxTime);

            //reset the last total time
            stats.lastTotalTime = stats.totalTime;
        }
    }

    class MethodStats {
        public String methodName;
        public long count;//�ܵĴ���
        public long totalTime;//���л���ʱ��
        public long lastTotalTime;//�����ڶ��λ���ʱ��
        public long maxTime;//���������ĺ�ʱ

        public MethodStats(String methodName) {
            this.methodName = methodName;
        }
    }

}
