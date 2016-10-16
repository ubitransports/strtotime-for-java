package com.wareninja.opensource.strtotime;


import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

/**
 * Created by jeremy on 12/10/16.
 */

public class SpecificTimeMatcher implements Matcher {

    private final Pattern specificTime = Pattern.compile("([2][0-3]|[0-1][0-9]|[1-9]):[0-5][0-9](:([0-5][0-9]))?");

    public Date tryConvert(String input, Date refDateStr) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(refDateStr);

        if (specificTime.matcher(input).matches()) {
            String[] w = input.split(":");
            int[] d = new int[w.length];
            for (int i = 0; i < w.length; i++) {
                d[i] = Integer.parseInt(w[i]);
            }
            //Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, d[0]);
            calendar.set(Calendar.MINUTE, d[1]);
            if (d.length == 3) {
                calendar.set(Calendar.SECOND, d[2]);
            } else {
                calendar.set(Calendar.SECOND, 0);
            }
            return calendar.getTime();
        }

        return null;
    }
}
