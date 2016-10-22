/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.wareninja.opensource.strtotime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

class MinutesMatcher implements Matcher {

    private final Pattern minutes = Pattern.compile("[\\-\\+]?\\d+ minutes");

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

        if (minutes.matcher(input).matches()) {
            int m = Integer.parseInt(input.split(" ")[0]);
            //Calendar calendar = Calendar.getInstance();
            
            calendar.add(Calendar.MINUTE, m);
            return calendar.getTime();
        }

        return null;
    }
}
