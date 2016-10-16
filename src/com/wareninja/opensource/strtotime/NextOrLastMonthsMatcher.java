/***
 *   Copyleft 2014 - WareNinja.com / Rumble In The Jungle!
 * 
 *  @author: yg@wareninja.com
 *  @see https://github.com/WareNinja
 *  disclaimer: I code for fun, dunno what I'm coding about :-)
 */

package com.wareninja.opensource.strtotime;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class NextOrLastMonthsMatcher implements Matcher {

    private final Pattern months = Pattern.compile("(next|last) ((day of this month)|(day of [\\-\\+]?\\d+ month)) ([2][0-3]|[0-1][0-9]|[1-9]):[0-5][0-9](:([0-5][0-9]))?");

    public Date tryConvert(String input, Date refDateStr) {

    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(refDateStr);
    	
        if (months.matcher(input).matches()) {
            String[] str = input.split(" ");
            if (str[0].equalsIgnoreCase("last")) {
                if (input.contains("last day of this month")) {
                    int lastDayOfThisMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                    calendar.set(Calendar.DAY_OF_MONTH, lastDayOfThisMonth);
                    calendar = setTimeToCalendar(calendar, (str[5]).split(":"));
                    return calendar.getTime();
                } else {
                    String nbMonthToAdd = str[3];
                    calendar.add(Calendar.MONTH, Integer.parseInt(nbMonthToAdd));
                    int lastDayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
                    calendar.set(Calendar.DAY_OF_MONTH, lastDayOfMonth);
                    calendar = setTimeToCalendar(calendar, (str[5]).split(":"));
                    return calendar.getTime();
                }
            }else{
                System.out.println("N'EST PAS IMPLEMENTE CAR NORMALEMENT PAS UTILISE");
            }
        }

        return null;
    }

    private Calendar setTimeToCalendar(Calendar c, String[] time) {
        Calendar date = c;
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
