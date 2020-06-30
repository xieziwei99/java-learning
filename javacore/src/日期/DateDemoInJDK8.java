package 日期;

import java.text.ParseException;
import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * @author xieziwei99
 * 2020-06-28
 */
public class DateDemoInJDK8 {

    public static void main(String[] args) throws ParseException {
        System.out.println(LocalDate.now());
        System.out.println(LocalTime.now());
        System.out.println(LocalDateTime.now());

        LocalDateTime localDateTime = LocalDateTime.of(2020, 5, 7, 13, 14);
        System.out.println(localDateTime);
        System.out.println(localDateTime.getYear());
        System.out.println(localDateTime.getMonth());
        System.out.println(localDateTime.getDayOfMonth());
        System.out.println(localDateTime.getDayOfWeek());

        LocalDateTime localDateTime1 = localDateTime.withHour(5).withMinute(20);
        System.out.println(localDateTime);
        System.out.println(localDateTime1);

        localDateTime1 = localDateTime.plusSeconds(12);
        System.out.println(localDateTime1);
        localDateTime1 = localDateTime.minusSeconds(-10);
        System.out.println(localDateTime1);

        Instant instant = Instant.now();    // 比北京时间慢8小时
        System.out.println(instant);
        System.out.println(instant.toEpochMilli());
        System.out.println(instant.getEpochSecond());

        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);
        System.out.println(offsetDateTime.toInstant());     // 和上面获得的 instant 一样

        System.out.println(LocalDateUtil.format(LocalDateTime.now()));
        System.out.println(LocalDateUtil.parse("2020-06-30 13:36:27"));

        System.out.println(System.currentTimeMillis());     // 1593497477287
        System.out.println(LocalDateUtil.format(LocalDateTime.now()));  // 2020-06-30 14:11:17
        System.out.println(LocalDateUtil.strToMilli("2020-06-30 14:11:17"));
        System.out.println(DateUtil.parse("2020-06-30 14:11:17").getTime());
        System.out.println(LocalDateUtil.milliToStr(1593497477287L));
    }
}

class LocalDateUtil {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static String format(LocalDateTime localDateTime) {
        return formatter.format(localDateTime);
    }

    public static LocalDateTime parse(String dateStr) {
        return LocalDateTime.parse(dateStr, formatter);
    }

    public static long strToMilli(String dateStr) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateStr, formatter);
        return localDateTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public static String milliToStr(long milli) {
        Instant instant = Instant.ofEpochMilli(milli);
        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.systemDefault());
        return format(localDateTime);
    }
}
