package 日期;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author xieziwei99
 * 2020-06-23
 */
public class DateDemo {

    public static void main(String[] args) throws ParseException {
        long currentTimeMillis = System.currentTimeMillis();
        System.out.println(currentTimeMillis);  // 1592909870652

        Date date = new Date();     // 无参构造：获取当前时间
        System.out.println(date);   // Tue Jun 23 18:58:35 GMT+08:00 2020

        Date date1 = new Date(currentTimeMillis);   // 使用毫秒数作为构造器参数
        System.out.println(date1);

        long time = date.getTime();     // Date对象 -> 时间戳
        System.out.println(time);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");
        String format = sdf.format(date);
        System.out.println(format);
        Date parse = sdf.parse("2020-06-24 19:32:01:501");
        System.out.println(parse);

        System.out.println(DateUtil.format(date));
        System.out.println(DateUtil.parse("2020-6-23 19:37:36"));

        Calendar calendar = Calendar.getInstance();
        System.out.println("当月的第几天，即几月几号的号 " + calendar.get(Calendar.DAY_OF_MONTH));
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        System.out.println(calendar.getTime());
        calendar.setTime(DateUtil.parse("2020-7-27 17:55:14"));
        System.out.println(calendar.getTime());
    }
}

class DateUtil {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static String format(Date date) {
        return sdf.format(date);
    }

    public static Date parse(String dateStr) throws ParseException {
        return sdf.parse(dateStr);
    }
}