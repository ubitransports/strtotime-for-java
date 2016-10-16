/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wareninja.opensource.strtotime;


import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class HoursAndMinutesMatcher implements Matcher {

    private final Pattern time = Pattern.compile("[\\-\\+]?\\d+ hour [\\-\\+]?\\d+ minutes");

    public Date tryConvert(String input, Date refDateStr) {

    	Calendar calendar = Calendar.getInstance();
        calendar.setTime(refDateStr);

        if (time.matcher(input).matches()) {
            int h = Integer.parseInt(input.split(" ")[0]);
            int m = Integer.parseInt(input.split(" ")[2]);

            //Calendar calendar = Calendar.getInstance();
            
            calendar.add(Calendar.HOUR,h);
            calendar.add(Calendar.MINUTE,m);
            return calendar.getTime();
        }

        return null;
    }
}
