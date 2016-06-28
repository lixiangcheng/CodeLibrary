package com.lee.util;

import java.util.List;
import java.util.Random;

/**
 * className:NumUtils
 * date:2016/4/7
 * time:1:06
 * description:数字，字符串处理的工具类
 */
public class NumStrUtils {

    private static List<Long> list;

    public static String getInviteCode() {
        String nums[] = new String[]{"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        StringBuilder s = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            String num = nums[random.nextInt(nums.length)];
            s.append(num);
        }
        return s.toString();

    }

    public static String LonglistToStr(List<Long> list) {
        StringBuffer stringBuffer = new StringBuffer();

        for (Long id : list) {
            stringBuffer.append(id + ",");
        }

        String result = stringBuffer.toString();
        if (!stringBuffer.toString().equals("")) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        return stringBuffer.toString();
    }

    public static String IntlistToStr(List<Integer> list) {
        StringBuffer stringBuffer = new StringBuffer();

        for (Integer id : list) {
            stringBuffer.append(id + ",");
        }
        String result = stringBuffer.toString();
        if (!stringBuffer.toString().equals("")) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        return stringBuffer.toString();
    }

    public static String IntArrayToStr(Integer[] array) {

        StringBuffer stringBuffer = new StringBuffer();

        for (Integer id : array) {
            stringBuffer.append(id + ",");
        }
        String result = stringBuffer.toString();
        if (!stringBuffer.toString().equals("")) {
            stringBuffer.deleteCharAt(stringBuffer.length() - 1);
        }
        return stringBuffer.toString();
    }

    /**
     *
     * @param str
     * @return
     */
    public static boolean isNum(String str)
    {
        if ((str == null) || ("".equals(str.trim()))) {
            return false;
        }
        if (("-".equals(str.trim())) || ("+".equals(str.trim()))) {
            return false;
        }
        return str.matches("^[-+]?(([0-9]+)([.]([0-9]+))?|([.]([0-9]+))?)$");
    }
}
