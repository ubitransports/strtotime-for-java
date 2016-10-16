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

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

class WeeksMatcher implements Matcher {

    private final Pattern weeks = Pattern.compile("[\\-\\+]?\\d+ (week|weeks)");

    public Date tryConvert(String input, Date refDateStr) {

    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(refDateStr);
    	
        if (weeks.matcher(input).matches()) {
            int w = Integer.parseInt(input.split(" ")[0]);
            //Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_YEAR, w * 7);
            return calendar.getTime();
        }

        return null;
    }
}
