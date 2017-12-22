package com.ztx.math.util;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 时间帮助类
 *
 * @author ztx
 */
public class DateAndTimeUtil {
    /**
     * 时间日期格式。
     */
    public static final String DATEANDTIMEFORM = "yyyy-MM-dd HH:mm:ss";
    /**
     * 日期格式。
     */
    public static final String DATEFORM = "yyyy-MM-dd";
    public static final String DATEFORM_1 = "yyyyMMdd";
    /**
     * 时间格式。
     */
    public static final String TIMEFORM = "HH:mm:ss";
    /**
     * 时间格式，没有秒。
     */
    public static final String TIMEFORM_MINUTE = "HH:mm";

    /**
     * 时间格式。月 日
     */
    public static final String MONTH_DAY = "MM月dd日";
    /**
     * 时间格式。月 日
     */
    public static final String MONTH__DAY = "MM-dd";

    /**
     * 获取日期和时间，格式为:"yyyy-MM-dd HH:mm:ss"
     *
     * @param time long型时间
     * @return 格式化时间字符串
     */
    public static String getStringDataAndTime(long time) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATEANDTIMEFORM);
        Date curDate = new Date(time);
        String Standby = formatter.format(curDate);
        return Standby;
    }

    /**
     * 得到当前时间的日期，格式为："yyyy-MM-dd"
     *
     * @return 格式化时间字符串
     */
    public static String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat(DATEFORM);
        return sdf.format(new Date());
    }

    public static String getCurrentDate(String formate) {
        SimpleDateFormat sdf = new SimpleDateFormat(formate);
        return sdf.format(new Date());
    }

    /**
     * 得到当前的时间，格式为："HH:mm:ss"
     *
     * @return 格式化时间字符串
     */
    public static String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat(TIMEFORM);
        return sdf.format(new Date());
    }

    /**
     * 得到年，格式为：12
     *
     * @return 格式化时间数字
     */
    public static int getCurrentYear() {
        Date date = new Date();
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1; // 0-based!
        int day = now.get(Calendar.DAY_OF_MONTH);
        return year;
    }

    /**
     * 得到当前月，格式为如：12
     *
     * @return 格式化时间数字
     */
    public static int getCurrentMonth() {
        Date date = new Date();
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1; // 0-based!
        int day = now.get(Calendar.DAY_OF_MONTH);
        return month;
    }

    /**
     * 得到当前的时间，格式为："HH:mm:ss"
     *
     * @return 格式化时间字符串
     */
    public static String getCurrentTime(String dateFormat) {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(new Date());
    }

    /**
     * 毫秒时间转字符串。
     *
     * @param timeInMils 当前时间。
     * @param dateFormat 待转时间字符串格式。
     * @return 时间字符串
     */
    public static String converTimeMilsToFormatStr(long timeInMils, String dateFormat) {
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        Date date = new Date(timeInMils);
        return df.format(date);
    }

    /**
     * 时间戳转为 分:秒
     *
     * @param time 时间戳
     * @return 分：秒
     */
    public static String timeMillis2min(long time) {
        String m = String.valueOf(time / 1000 / 60);
        String s = String.valueOf(time / 1000 % 60);
        if (m.length() == 1) {
            m = "0" + m;
        }
        if (s.length() == 1) {
            s = "0" + s;
        }
        return m + ":" + s;
    }

    /**
     * 天数的毫秒值
     *
     * @param differDays 天数。
     * @return 天数的毫秒值
     */
    public static long getDifferMillis(int differDays) {
        return differDays * 24 * 60 * 60 * 1000;
    }

    /**
     * 格式化日期
     *
     * @param c      日历对象
     * @param format 时间格式
     * @return 时间字符串
     */
    public static String getFormatDate(Calendar c, String format) {
        return getFormatDate(c.getTime(), format);
    }

    /**
     * 时间格式为yyyy-MM-dd HH:mm:ss
     *
     * @param c 日历对象
     * @return 时间字符串
     */
    public static String getFormatDate(Calendar c) {
        return getFormatDate(c.getTime(), DATEANDTIMEFORM);
    }

    /**
     * 根据日期对象返回时间格式。
     *
     * @param d      日期对象
     * @param format 时间格式
     * @return 时间字符串
     */
    public static String getFormatDate(Date d, String format) {
        SimpleDateFormat df = new SimpleDateFormat(format);
        return df.format(d);
    }

    /**
     * 获取时间对应的星期数， 1——周一，2——周二，3——周三...
     *
     * @param timeInMils 当前时间毫秒。
     * @return 一周的第几天
     */
    public static int getWeekDay(long timeInMils) {
        Calendar nowCal = Calendar.getInstance();
        Date nowDate = new Date(timeInMils);
        nowCal.setTime(nowDate);
        int nowNum = nowCal.get(Calendar.DAY_OF_WEEK);
        if (nowNum - 1 == 0) {
            return 7;
        } else {
            return nowNum - 1;
        }
    }

    /**
     * 返回当前时间毫秒数
     *
     * @param strData    时间字符串
     * @param dateFormat 时间格式
     * @return long型时间
     */
    public static long ConverStringToLong(String strData, String dateFormat) {
        Date date = converStringToDate(strData, dateFormat);
        if (date != null) {
            return date.getTime();
        }
        return 0;
    }

    /**
     * 时间字符串转date对象。
     *
     * @param strData    时间字串(如：2014-05-12)
     * @param dateFormat 时间格式（如："yyyy-MM-dd"）
     * @return Date对象
     */
    public static Date converStringToDate(String strData, String dateFormat) {
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        try {
            Date date = df.parse(strData);
            return date;
        } catch (Exception ex) {
            // Loger.e(ex);
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 格式化时间字串
     */
    // public static String formatDateString(String strData, String dateFormat)
    // {
    // SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
    // try {
    // Date date = sdf.parse(strData);
    // return sdf.format(date);
    // } catch (Exception ex) {
    // ex.printStackTrace();
    // return null;
    // }
    //
    // }

    /**
     * 获取当前时间的日历对象。
     *
     * @return 日历对象
     */
    public static Calendar getCurrentCalendar() {
        Calendar nowCal = Calendar.getInstance();
        Date nowDate = new Date(System.currentTimeMillis());
        nowCal.clear();
        nowCal.setTime(nowDate);
        return nowCal;
    }

    /**
     * 时间字符串转Calendar对象。
     *
     * @param strData    时间字串(如：2014-05-12)
     * @param dateFormat 时间格式（如："yyyy-MM-dd"）
     * @return Calendar对象
     */
    public static Calendar ConverStringToCalendar(String strData, String dateFormat) {
        SimpleDateFormat df = new SimpleDateFormat(dateFormat);
        Calendar calendar = Calendar.getInstance();
        try {
            Date date = df.parse(strData);
            calendar.setTime(date);
            return calendar;
        } catch (Exception ex) {
            return null;
        }
    }

    /**
     * 计算相隔天数
     *
     * @param startdayTimeMill 开始毫秒时间。
     * @param enddayTimeMill   结束毫秒时间。
     * @return 间隔天数。
     */
    public static int getIntervalDays(long startdayTimeMill, long enddayTimeMill) {
        if (startdayTimeMill < enddayTimeMill) {
            long cal = startdayTimeMill;
            startdayTimeMill = enddayTimeMill;
            enddayTimeMill = cal;
        } else {
            System.out.println("the startTime > endTime");
            return -1;
        }
        long ei = startdayTimeMill - enddayTimeMill;
        return (int) (ei / (1000 * 60 * 60 * 24));
    }

    /**
     * 获得某个时间到最近下月份的某天的时间
     *
     * @param n
     *            1-31 之间的某天
     * @param setTime
     *            某个时间
     * @return
     */
    // public static Calendar getToNextMonthDay(int n, long setTime) {
    //
    // Calendar canlendar = Calendar.getInstance();
    // canlendar.clear();
    // canlendar.setTimeInMillis(setTime);
    // canlendar.add(Calendar.MONTH, 1);
    // int maxDay = canlendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    // if (maxDay < n) {
    // // canlendar.add(Calendar.MONTH, 1);
    // getToNextMonthDay(n, canlendar.getTimeInMillis());
    // } else {
    // canlendar.set(Calendar.DAY_OF_MONTH, n);
    // }
    // return canlendar;
    // }

    /**
     * 计算in数组中距离 nowDay最近的一天
     *
     * @param in     日期列表
     * @param nowDay 目标日期
     * @return 最近的一天
     */
    public static int getResultDifferDay(int[] in, int nowDay) {
        int result = 0;
        for (int i = 0; i < in.length; i++) {
            if (in[i] >= nowDay) {
                result = in[i];
                break;
            }
        }

        if (result == 0) {
            result = in[0];
        }
        return result;
    }

    /**
     * 精确计算两个日历对象之间相隔天数的方法
     *
     * @param d1 日历对象
     * @param d2 日历对象
     * @return 相隔天数。
     */
    public static int getDaysBetween(Calendar d1, Calendar d2) {
        if (d1.after(d2)) {
            Calendar swap = d1;
            d1 = d2;
            d2 = swap;
        }
        int days = d2.get(Calendar.DAY_OF_YEAR) - d1.get(Calendar.DAY_OF_YEAR);
        int y2 = d2.get(Calendar.YEAR);
        if (d1.get(Calendar.YEAR) != y2) {
            d1 = (Calendar) d1.clone();
            do {
                days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);// 得到当年的实际天数
                d1.add(Calendar.YEAR, 1);
            } while (d1.get(Calendar.YEAR) != y2);
        }
        return days;
    }

    /**
     * 获得两个时间戳相隔小时数
     * 这个除去了天数
     *
     * @param begin 起始时间
     * @param end   结束时间
     * @return 相隔
     */
    public static Long getHoursBetween(Long begin, Long end) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = end - begin;
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return hour;
    }

    /**
     * 获得两个时间戳相隔分钟数
     *
     * @param begin 起始时间
     * @param end   结束时间
     * @return 相隔
     */
    public static Long getMinBetween(Long begin, Long end) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = end - begin;
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return min;
    }

    /**
     * 获得两个时间戳相隔真实小时数
     * 没有除去天数  大于24小时同样返回
     *
     * @param begin 起始时间
     * @param end   结束时间
     * @return 相隔
     */
    public static Long getRealHoursBetween(Long begin, Long end) {
        long nh = 1000 * 60 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = end - begin;
        // 计算差多少天
        // 计算差多少小时
        long hour = diff / nh;
        // 计算差多少分钟
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return hour;
    }

    /**
     * 计算两个日期相隔天数 (to-from)
     *
     * @param from 日期对象
     * @param to   日期对象
     * @return 相隔天数
     */
    public static int getDiffDays(Date from, Date to) {
        int day = (int) ((to.getTime() - from.getTime()) / (24 * 60 * 60 * 1000));
        return day;
    }

    /**
     * 计算两个日期相隔天数 (to-from)
     *
     * @param from       开始时间(2015-02-01)
     * @param fromformat 时间格式
     * @param to         结束时间(2015-04-01)
     * @param toformat   时间格式
     * @return 相隔天数
     */
    public static int getDiffDays(String from, String fromformat, String to, String toformat) {
        Date beginDate = converStringToDate(from, fromformat);
        Date endDate = converStringToDate(to, toformat);
        return getDiffDays(beginDate, endDate);
    }

    /**
     * 获得离当前时间（或指定时间）最近的一个特定时间(特定时间小于当前时间，则寻找下月最近日期)
     *
     * @param setTime 当前时间（或指定时间）
     * @param day     特定日期
     * @param hour    特定时间
     * @param minute  特定分钟
     * @param second  特定秒
     * @return 相距的毫秒数
     */
    public static long GetFixedTimeInMils(long setTime, int day, int hour, int minute, int second) {
        Calendar canlendar = Calendar.getInstance();
        canlendar.clear();
        canlendar.setTimeInMillis(setTime);
        int maxDay = canlendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        if (maxDay > day) {
            canlendar.set(Calendar.DAY_OF_MONTH, day);
            canlendar.set(Calendar.HOUR_OF_DAY, hour);
            canlendar.set(Calendar.MINUTE, minute);
            canlendar.set(Calendar.SECOND, second);
            canlendar.set(Calendar.MILLISECOND, 0);
        } else {
            canlendar.add(Calendar.MONTH, 1);
            canlendar.set(Calendar.DAY_OF_MONTH, day);
            canlendar.set(Calendar.HOUR_OF_DAY, hour);
            canlendar.set(Calendar.MINUTE, minute);
            canlendar.set(Calendar.SECOND, second);
            canlendar.set(Calendar.MILLISECOND, 0);
        }
        return canlendar.getTimeInMillis();

    }

    /**
     * 获取指定年月中的某天某时某分某秒
     *
     * @param setTime 指定的年月
     * @param day     某天
     * @param hour    某时
     * @param minute  某分
     * @param second  某秒
     * @return 指定时间的毫秒数
     */
    public static long getSetTimeInMils(long setTime, int day, int hour, int minute, int second) {
        Calendar canlendar = Calendar.getInstance();
        canlendar.clear();
        canlendar.setTimeInMillis(setTime);
        canlendar.set(Calendar.DAY_OF_MONTH, day);
        canlendar.set(Calendar.HOUR_OF_DAY, hour);
        canlendar.set(Calendar.MINUTE, minute);
        canlendar.set(Calendar.SECOND, second);
        canlendar.set(Calendar.MILLISECOND, 0);
        return canlendar.getTimeInMillis();
    }

    /**
     * 获取指定年月日中的某时某分某秒
     *
     * @param setTime 指定年月日
     * @param hour    某时
     * @param minute  某分
     * @param second  某秒
     * @return 指定时间的毫秒数
     */
    public static long getSetTimeInMils(long setTime, int hour, int minute, int second) {
        Calendar canlendar = Calendar.getInstance();
        canlendar.clear();
        canlendar.setTimeInMillis(setTime);

        canlendar.set(Calendar.HOUR_OF_DAY, hour);
        canlendar.set(Calendar.MINUTE, minute);
        canlendar.set(Calendar.SECOND, second);
        canlendar.set(Calendar.MILLISECOND, 0);
        return canlendar.getTimeInMillis();
    }

    /**
     * int类型转星期字符串。（1<=int<=7）
     *
     * @param i （1<=int<=7）
     * @return 周
     */
    public static String ConverWeekDayToStr(int i) {
        if (i > 7 || i < 1) {
            return null;
        }
        String str = "";
        switch (i) {
            case 1:
                str += "周一";
                break;
            case 2:
                str += "周二";
                break;
            case 3:
                str += "周三";
                break;
            case 4:
                str += "周四";
                break;
            case 5:
                str += "周五";
                break;
            case 6:
                str += "周六";
                break;
            case 7:
                str += "周日";
                break;
        }
        return str;
    }

    /**
     * 根据给定日期和相差天数计算日期
     *
     * @param from       日期
     * @param differDays 相差天数
     * @return 日期
     */
    public static Date getDiffDate(Date from, int differDays) {
        return new Date(from.getTime() + getDifferMillis(differDays));
    }

    /**
     * 根据给定日期和相差天数计算日期
     *
     * @param from       日期
     * @param differDays 相差天数
     * @param format     时间格式
     * @return 格式化的日期
     */
    public static String getDiffDate(String from, int differDays, String format) {
        Date fromDate = converStringToDate(from, format);
        Date toDate = getDiffDate(fromDate, differDays);
        return getFormatDate(toDate, format);
    }

    /**
     * 20以内的随机数，分散请求。
     */
    private static final int RANDOMMINUTE = 20;

    /**
     * 根据传入的日期和时间，产生一个随机分钟数，用于定时请求后台。
     *
     * @param calendar 日历对象
     * @param hour     一天中的某个时间
     * @return 返回一个随机的分钟数日历对象。
     */
    public static Calendar BuidingSendTime(Calendar calendar, int hour) {
        // 增加一个20以内的随机数来表示分，防止所有用户同一时间一起访问服务器。
        int minute = (int) (Math.random() * RANDOMMINUTE);
        if (calendar != null) {
            if (hour <= calendar.get(Calendar.HOUR_OF_DAY)) {
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            }
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);
            System.out.println("ztx-----    定时＊时间：    " + calendar.get(calendar.YEAR) + "  " + calendar.get(calendar.MONTH) + "  "
                    + calendar.get(calendar.DAY_OF_MONTH) + "  " + calendar.get(calendar.HOUR_OF_DAY) + "  " + calendar.get(calendar.MINUTE)
                    + "  " + calendar.get(calendar.SECOND) + "  " + calendar.get(calendar.MILLISECOND));
        }

        return calendar;
    }

    /**
     * 得到一个将来最近的日期时间
     *
     * @param hour   一天中的某个时间小时
     * @param minute 一天中的某个时间分钟
     * @param second 一天中的某个时间秒数
     * @return 返回一个随机的分钟数日历对象。
     */
    public static Calendar getNestTime(int hour, int minute, int second) {
        Calendar calendar = Calendar.getInstance();
        int nowHour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour < nowHour) {
            calendar.add(Calendar.DAY_OF_MONTH, 1);
        } else if (hour == nowHour) {
            int nowMinute = calendar.get(Calendar.MINUTE);
            if (minute < nowMinute) {
                calendar.add(Calendar.DAY_OF_MONTH, 1);
            } else if (minute == nowMinute) {
                int nowSecond = calendar.get(Calendar.SECOND);
                if (second <= nowSecond) {
                    calendar.add(Calendar.DAY_OF_MONTH, 1);
                }
            }
        }
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
//        QLog.d("ztx-----    定时＊时间：    " + calendar.get(calendar.YEAR) + "  " + calendar.get(calendar.MONTH) + "  "
//                + calendar.get(calendar.DAY_OF_MONTH) + "  " + calendar.get(calendar.HOUR_OF_DAY) + "  " + calendar.get(calendar.MINUTE)
//                + "  " + calendar.get(calendar.SECOND) + "  " + calendar.get(calendar.MILLISECOND));
        return calendar;
    }

    /**
     * 将分钟数进行格式化：如：100分钟——1小时40分钟
     *
     * @param minut 分钟数
     * @return 格式化之后的时间字符串
     */
    public static String minutForm(int minut) {
        int hours = minut / 60;
        int remain = minut % 60;
        if (hours > 0) {
            return hours + "小时" + remain + "分钟";
        } else {
            return remain + "分钟";
        }
    }

    /**
     * 秒数结构化，如：100秒——1分40秒
     *
     * @param second 总秒数
     * @return 有结构的时分秒字符串
     */
    public static String secondForm(int second) {
        int hours = second / 60 / 60;
        int minute = (second / 60) % 60;
        second = second % 60;
        if (hours >= 1) {
            return hours + "小时" + minute + "分" + second + "秒";
        } else if (minute >= 1) {
            return minute + "分" + second + "秒";
        } else {
            return second + "秒";
        }
    }

    /**
     * 将毫秒数，转成十分秒格式，hh:mm:ss
     *
     * @param millis 毫秒数字
     * @return 格式化后的时分秒
     */
    public static String getTimeFromMillis(long millis) {
        String strRecordingTime = null;
        int seconds = (int) (millis / 1000);
        int minutes = seconds / 60;
        int hours = minutes / 60;

        if (hours >= 0 && hours < 10) {
            strRecordingTime = "0" + hours + ":";//时
        } else if (hours < 0) {
            strRecordingTime = "00:";//时
        } else {
            strRecordingTime = hours + ":";//时
        }
        if (hours > 0) {
            minutes = minutes % 60;
        }
        if (minutes >= 0 && minutes < 10) {
            strRecordingTime += "0" + minutes + ":";//分
        } else if (minutes < 0) {
            strRecordingTime = "00:";//分
        } else {
            strRecordingTime += minutes + ":";//分
        }
        seconds = seconds % 60;
        if (seconds >= 0 && seconds < 10) {
            strRecordingTime += "0" + seconds;//秒
        } else if (seconds < 0) {
            strRecordingTime = "00:";//秒
        } else {
            strRecordingTime += seconds;//秒
        }
        return strRecordingTime;
    }



}
