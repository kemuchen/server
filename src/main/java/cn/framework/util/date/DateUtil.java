package cn.framework.util.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName DateUtil
 * @Desc
 * @Author 柯雷
 * @Date 2020-04-27 15:20
 * @Version 1.0
 */
public class DateUtil {

    /**
     * @param ：@param  startDate
     * @param ：@param  endDate
     * @param ：@return
     * @return ：int
     * @throws
     * @Title：getGapCount
     * @Description：获取日之间相差天数
     */
    public static int getGapCount(Date startDate, Date endDate) {
        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.setTime(startDate);
        fromCalendar.set(Calendar.HOUR_OF_DAY, 0);
        fromCalendar.set(Calendar.MINUTE, 0);
        fromCalendar.set(Calendar.SECOND, 0);
        fromCalendar.set(Calendar.MILLISECOND, 0);

        Calendar toCalendar = Calendar.getInstance();
        toCalendar.setTime(endDate);
        toCalendar.set(Calendar.HOUR_OF_DAY, 0);
        toCalendar.set(Calendar.MINUTE, 0);
        toCalendar.set(Calendar.SECOND, 0);
        toCalendar.set(Calendar.MILLISECOND, 0);

        return (int) ((toCalendar.getTime().getTime() - fromCalendar.getTime().getTime()) / (1000 * 60 * 60 * 24));
    }

    /**
     * @Description: 获取日之间相差天数
     * @Params: [startDate, endDate, format]
     * @return: int
     * @Author: 柯雷
     * @Date: 2019/11/4 17:25
     */
    public static int getGapCount(String startDate, String endDate, String format) throws Exception {
        return getGapCount(parseDate(startDate, format), parseDate(endDate, format));
    }

    /**
     * @param ：@param  date
     * @param ：@param  format
     * @param ：@return
     * @return ：Date
     * @throws
     * @Title：formatDate
     * @Description：格式化日期
     */
    public static String formatDate(Date date, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.format(date);
    }

    /**
     * @param ：@param  date
     * @param ：@param  format
     * @param ：@return
     * @param ：@throws ParseException
     * @return ：Date
     * @throws
     * @Title：parseDate
     * @Description： string转date类型
     */
    public static Date parseDate(String date, String format) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        return dateFormat.parse(date);
    }

    /**
     * @Title：getDqrq @Description：获取当前日期 @param ：@param format @param
     * ：@return @return ：String @throws
     */
    public static String getNow(String format) {
        return formatDate(new Date(), format);
    }

    /**
     * 当前日期增加天数
     *
     * @param day        增加天数
     * @param isWeekdays 是否为工作日
     * @return
     */
    public static String addDate(int day, boolean isWeekdays, String format) {
        return addDate(new Date(), day, isWeekdays, format);
    }

    /**
     * 日期增加天数
     *
     * @param date       日期
     * @param day        增加天数
     * @param isWeekdays 是否为工作日
     * @return
     */
    public static String addDate(Date date, int day, boolean isWeekdays, String format) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(format); // 日期格式
        long time = date.getTime(); // 得到指定日期的毫秒数

        int addDay = 0;
        for (int i = 0; i < Math.abs(day); i++) {
            if (day > 0) {
                addDay++;
            } else {
                addDay--;
            }
            if (isWeekdays && isWeekend(new Date(time + addDay * 24 * 60 * 60 * 1000))) {
                i--;
            }
        }
        long temp = addDay;
        time += temp * 24 * 60 * 60 * 1000; // 相加得到新的毫秒数
        return dateFormat.format(new Date(time)); // 将毫秒数转换成日期
    }

    /**
     * 判断日期是否为周末
     *
     * @param date
     * @return
     */
    public static boolean isWeekend(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        if (cal.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY || cal.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 增加月份
     *
     * @param month
     * @return
     */
    public static String addMonth(int month) {
        return addMonth(new Date(), month);
    }

    /**
     * 增加月份
     *
     * @param date
     * @param Month
     * @return
     */
    public static String addMonth(Date date, int Month) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMM"); // 日期格式
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, Month);// 把日期往后增加一年.整数往后推,负数往前移动
        date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
        return dateFormat.format(date);
    }

    /**
     * @Description: 获取时间戳
     * @Param:
     * @return:
     * @Author: 柯雷
     * @Date: 2019/9/17 23:06
     */
    public static Long getTime(String date, String format) throws Exception {
        Date newDate = parseDate(date, format);
        return newDate.getTime();
    }
}
