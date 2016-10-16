package com.wareninja.opensource.strtotime;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by jeremy on 12/10/16.
 */

public class NextOrLastOnDayMatcher implements Matcher {

    private static final String TAG = NextOrLastOnDayMatcher.class.getSimpleName();
    private final Pattern time = Pattern.compile("(next|last) (monday|MONDAY|Monday|tuesday|TUESDAY|Tuesday|wednesday|WEDNESDAY|Wednesday|thursday|THURSDAY|Thursday|friday|FRIDAY|Friday|saturday|SATURDAY|Saturday|sunday|SUNDAY|Sunday) ([2][0-3]|[0-1][0-9]|[1-9]):[0-5][0-9](:([0-5][0-9]))?");
    private final String[] DAYS = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

    public Date tryConvert(String input, Date refDateStr) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(refDateStr);
        if (time.matcher(input).matches()) {
            String[] str = input.split(" ");
            if (str[0].equalsIgnoreCase("next")) {
                String param = str[1];
                if (isOfTypeDays(param)) {
                    DateTime ref = new DateTime(refDateStr);
                    DateTime d = ref.dayOfWeek().setCopy(getDateTimeConstants(param));
                    if (d.getMillis() < ref.getMillis()) {
                        d = d.plusWeeks(1);
                        d = d.dayOfWeek().setCopy(getDateTimeConstants(param));
                    }
                    Calendar date = Calendar.getInstance();
                    date.setTime(new Date(d.getMillis()));
                    date = setTimeToDate(date,str[2].split(":"));
                    return date.getTime();
                } else {
                    System.out.println("N'est pas du type jour");
                    // Log.i(TAG,"n'est pas du type jour");
                }
            } else if (str[0].equalsIgnoreCase("last")) {
                System.out.println("N'EST PAS IMPLEMENTE CAR NORMALEMENT PAS UTILISE");
            }
        }
        return null;
    }

    private boolean isOfTypeDays(String param) {
        for (String d : DAYS) {
            if (d.equalsIgnoreCase(param)) return true;
        }
        return false;
    }

    private int getDateTimeConstants(String param) {
        if (param.equalsIgnoreCase("Monday")) return DateTimeConstants.MONDAY;
        if (param.equalsIgnoreCase("Tuesday")) return DateTimeConstants.TUESDAY;
        if (param.equalsIgnoreCase("Wednesday")) return DateTimeConstants.WEDNESDAY;
        if (param.equalsIgnoreCase("Thursday")) return DateTimeConstants.THURSDAY;
        if (param.equalsIgnoreCase("Friday")) return DateTimeConstants.FRIDAY;
        if (param.equalsIgnoreCase("Saturday")) return DateTimeConstants.SATURDAY;
        if (param.equalsIgnoreCase("Sunday")) return DateTimeConstants.SUNDAY;
        return -1;
    }

    private Calendar setTimeToDate(Calendar d,String[] time){
        Calendar date = d;
        int[] dd = new int[time.length];
        for (int i = 0; i < time.length; i++) {
            dd[i] = Integer.parseInt(time[i]);
        }
        date.set(Calendar.HOUR, dd[0]);
        date.set(Calendar.MINUTE, dd[1]);
        if (dd.length == 3) {
            date.set(Calendar.SECOND, dd[2]);
        } else {
            date.set(Calendar.SECOND, 0);
        }
        return date;
    }
}
