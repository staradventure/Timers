package com.TwoFish.timers;

public class Utils {
    public static String transform(int seconds) {
        int hours = seconds / 3600; // 将秒转换为小时
        int minutes = (seconds % 3600) / 60; // 计算剩余分钟
        int remainingSeconds = seconds % 60; // 计算剩余秒数

        // 返回格式化的字符串
        return String.format("%d小时%d分钟%d秒", hours, minutes, remainingSeconds);
    }
}
