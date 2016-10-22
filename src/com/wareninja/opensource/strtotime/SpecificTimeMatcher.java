package com.wareninja.opensource.strtotime;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import org.apache.commons.lang3.time.DateUtils;

/**
 * Created by jeremy on 12/10/16.
 */

public class SpecificTimeMatcher implements Matcher {

    private final Pattern specificTime = Pattern.compile("([2][0-3]|[0-1][0-9]|[1-9]):[0-5][0-9](:([0-5][0-9]))?");

    public Date tryConvert(String input, String refDateStr) {

    	if (refDateStr.isEmpty()) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(refDateStr));
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

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
