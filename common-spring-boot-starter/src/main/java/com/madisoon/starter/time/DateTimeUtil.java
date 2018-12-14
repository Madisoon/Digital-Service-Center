package com.madisoon.starter.time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Description:
 * JAVA 新的时间类编写的常用时间方法
 *
 * @author Msater Zg
 * @date 2018/11/15 4:37 PM
 */
public class DateTimeUtil {
    /**
     * 根据不同格式获取当前时间
     *
     * @param formMatter 时间格式
     * @return 时间字符串
     */
    private String getNowTimeByFormMatter(String formMatter) {

        LocalDateTime localDateTime = LocalDateTime.now();

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(formMatter);

        return localDateTime.format(timeFormatter);
    }
}
