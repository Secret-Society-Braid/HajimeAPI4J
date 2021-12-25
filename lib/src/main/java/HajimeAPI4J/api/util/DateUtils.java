package HajimeAPI4J.api.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import HajimeAPI4J.api.HajimeAPI4J;

import java.text.ParseException;

public class DateUtils {

    // generate constructor that throws UnsupportedOperationException whose message is "This class is not intended to be instantiated."
    private DateUtils() {
        throw new UnsupportedOperationException("This class is not intended to be instantiated.");
    }

    // return the date that is converted from the given string
    public static Date getDateFromString(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat(HajimeAPI4J.DATE_FORMAT_STRING);
        Date date = null;
        try {
            date = sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    // return the string that is converted from the given date
    public static String getStringFromDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(HajimeAPI4J.DATE_FORMAT_STRING);
        return sdf.format(date);
    }

    public static int monthsBetween(Date startDate, Date endDate) {
        return (int) ((endDate.getTime() - startDate.getTime()) / (30 * 24 * 60 * 60 * 1000L));
    }

    public static int daysBetween(Date startDate, Date endDate) {
        return (int) ((endDate.getTime() - startDate.getTime()) / (24 * 60 * 60 * 1000L));
    }

    public static int hoursBetween(Date startDate, Date endDate) {
        return (int) ((endDate.getTime() - startDate.getTime()) / (60 * 60 * 1000L));
    }

    public static int minutesBetween(Date startDate, Date endDate) {
        return (int) ((endDate.getTime() - startDate.getTime()) / (60 * 1000L));
    }

    public static int secondsBetween(Date startDate, Date endDate) {
        return (int) ((endDate.getTime() - startDate.getTime()) / (1000L));
    }

    public static int millisecondsBetween(Date startDate, Date endDate) {
        return (int) (endDate.getTime() - startDate.getTime());
    }

}