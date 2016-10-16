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

public class MonthsMatcher implements Matcher {

    private final Pattern months = Pattern.compile("[\\-\\+]?\\d?\\d+ month");

    public Date tryConvert(String input, Date refDateStr) {

    	Calendar calendar = Calendar.getInstance();
    	calendar.setTime(refDateStr);
    	
        if (months.matcher(input).matches()) {
            int w = Integer.parseInt(input.split(" ")[0]);
            //Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MONTH, w);
            return calendar.getTime();
        }

        return null;
    }
}
