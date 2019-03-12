package com.madisoon.starter.time;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * JAVA 新的时间类编写的常用时间方法
 *
 * @author Msater Zg
 * @date 2018/11/15 4:37 PM
 */
public class DateTimeUtil {

    private final String FORM_MAT_YYYY_MM_DD = "yyyy-MM-dd";

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

    /**
     * @param startTime 开始时间, endTime 结束时间
     * @return java.util.List<java.lang.String>
     * @author zg
     * Description: 返回两个时间中的其他时间并输出，如2018-01-01，2018-01-07，则发挥[2018-01-01, 2018-01-02,...2018-01-07]
     * @date 11:40 AM 2019/3/7
     **/
    public List<String> computingTime(String startTime, String endTime) {
        Long len = daysBetween(startTime, endTime, FORM_MAT_YYYY_MM_DD, "day");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(FORM_MAT_YYYY_MM_DD);
        List<String> list = new ArrayList<>(10);
        list.add(startTime);
        LocalDateTime startDateTime;
        startDateTime = LocalDateTime.parse(startTime, timeFormatter);
        for (int i = 0; i < len; i++) {
            LocalDateTime dateTime = startDateTime.minusDays(1L);
            list.add(dateTime.format(timeFormatter));
        }
        return list;
    }

    public Long daysBetween(String startTime, String endTime, String formMat, String type) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(formMat);
        LocalDateTime startDateTime;
        startDateTime = LocalDateTime.parse(startTime, timeFormatter);
        LocalDateTime endDateTime;
        endDateTime = LocalDateTime.parse(endTime, timeFormatter);
        Duration duration = Duration.between(startDateTime, endDateTime);

        Long count;
        switch (type) {
            case "day":
                count = duration.toDays();
                break;
            case "hour":
                count = duration.toHours();
                break;
            case "minute":
                count = duration.toMinutes();
                break;
            case "second":
                count = duration.toMillis();
                break;
            default:
                count = 0L;
                break;
        }
        return count;
    }
}
