/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wareninja.opensource.strtotime;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

public class HoursMatcher implements Matcher {

    private final Pattern hours = Pattern.compile("[\\-\\+]?\\d+ hour");

    public Date tryConvert(String input, Date refDateStr) {

    	Calendar calendar = Calendar.getInstance();
        calendar.setTime(refDateStr);
    	
        if (hours.matcher(input).matches()) {
            int m = Integer.parseInt(input.split(" ")[0]);
            //Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.HOUR, m);
            return calendar.getTime();
        }

        return null;
    }
}
