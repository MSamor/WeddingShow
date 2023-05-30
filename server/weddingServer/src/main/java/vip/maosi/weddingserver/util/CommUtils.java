package vip.maosi.weddingserver.util;

import org.apache.commons.lang3.time.FastDateFormat;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalField;
import java.time.temporal.WeekFields;
import java.util.*;

public class CommUtils {
    private static final String UA_MACOS_SAFARI = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/605.1.15 (KHTML, like Gecko) Version/16.0 Safari/605.1.15";
    private static final String UA_MACOS_CHROME = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.5005.40 Safari/537.36";
    private static final String UA_MACOS_FIREFOX = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:98.0) Gecko/20100101 Firefox/98.0";
    private static final String UA_MACOS_EDGE = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.75 Safari/537.36 Edg/100.0.1185.39";

    private static final String UA_WINDOWS_CHROME = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/101.0.4951.61 Safari/537.36";
    private static final String UA_WINDOWS_FIREFOX = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:100.0) Gecko/20100101 Firefox/100.0";
    private static final String UA_WINDOWS_EDGE = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/103.0.5026.0 Safari/537.36 Edg/103.0.1254.0";

    private static final String[] ALL_UA = {UA_MACOS_SAFARI, UA_MACOS_CHROME, UA_MACOS_FIREFOX, UA_MACOS_EDGE, UA_WINDOWS_CHROME, UA_WINDOWS_FIREFOX, UA_WINDOWS_EDGE};
    private static final Random RANDOM = new Random();

    private static final FastDateFormat todayFormat = FastDateFormat.getInstance("今天 HH:mm", TimeZone.getTimeZone("GMT+8"), Locale.CHINA);
    private static final FastDateFormat yesterdayFormat = FastDateFormat.getInstance("昨天 HH:mm", TimeZone.getTimeZone("GMT+8"), Locale.CHINA);
    private static final FastDateFormat otherDayFormat = FastDateFormat.getInstance("M月d日 HH:mm", TimeZone.getTimeZone("GMT+8"), Locale.CHINA);
    private static final FastDateFormat otherYearDayFormat = FastDateFormat.getInstance("yyyy年M月d日 HH:mm", TimeZone.getTimeZone("GMT+8"), Locale.CHINA);
    public static final FastDateFormat yearMonthDayFormat = FastDateFormat.getInstance("yyyy年M月d日 HH:mm", TimeZone.getTimeZone("GMT+8"), Locale.CHINA);
    public static final FastDateFormat simpleYearMonthDayFormat = FastDateFormat.getInstance("yyyy-MM-dd", TimeZone.getTimeZone("GMT+8"), Locale.CHINA);

    public static String randomUA() {
        return ALL_UA[RANDOM.nextInt(ALL_UA.length)];
    }

    public static String formatPubdate(Date date) {
        if (date == null) return "";
        long current = System.currentTimeMillis();
        long time = date.getTime();
        long delta = current - time;
        long m, h;
        if (delta < 60 * 1000) {
            return "刚刚";
        } else if ((m = delta / (60 * 1000)) < 60) {
            return m + "分钟前";
        } else if ((h = delta / (60 * 60 * 1000)) <= 3) {
            return h + "小时前";
        } else {
            var calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
            calendar.setTimeInMillis(current);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            calendar.set(Calendar.MILLISECOND, 0);
            var today = calendar.getTime();
            if (date.equals(today) || date.after(today)) {
                return todayFormat.format(date);
            } else {
                calendar.add(Calendar.DATE, -1);
                Date yesterday = calendar.getTime();
                if (date.equals(yesterday) || date.after(yesterday)) {
                    return yesterdayFormat.format(date);
                } else if (date.getYear() == today.getYear()) {
                    return otherDayFormat.format(date);
                } else {
                    return otherYearDayFormat.format(date);
                }
            }
        }
    }

    public static String formatPubdate2(Date date) {
        if (date == null) return "";
        long current = System.currentTimeMillis();
        var calendar = Calendar.getInstance(TimeZone.getTimeZone("GMT+8"));
        calendar.setTimeInMillis(current);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        var today = calendar.getTime();
        if (date.equals(today) || date.after(today)) {
            return todayFormat.format(date);
        } else {
            calendar.add(Calendar.DATE, -1);
            Date yesterday = calendar.getTime();
            if (date.equals(yesterday) || date.after(yesterday)) {
                return yesterdayFormat.format(date);
            } else if (date.getYear() == today.getYear()) {
                return otherDayFormat.format(date);
            } else {
                return otherYearDayFormat.format(date);
            }
        }

    }

    public static Date getStartDayOfWeek(TemporalAccessor date) {
        TemporalField fieldIso = WeekFields.of(DayOfWeek.MONDAY, 1).dayOfWeek();
        LocalDate localDate = LocalDate.from(date);
        localDate = localDate.with(fieldIso, 1);
        return Date.from(localDate.atStartOfDay(ZoneId.of("GMT+8")).toInstant());
    }

    public static Date getEndDayOfWeek(TemporalAccessor date) {
        TemporalField fieldIso = WeekFields.of(DayOfWeek.MONDAY, 1).dayOfWeek();
        LocalDate localDate = LocalDate.from(date);
        localDate = localDate.with(fieldIso, 7);
        return Date.from(localDate.atStartOfDay(ZoneId.of("GMT+8")).toInstant());
    }
}
