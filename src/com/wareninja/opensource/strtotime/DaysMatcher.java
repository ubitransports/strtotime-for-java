/***
 *   Copyleft 2014 - WareNinja.com / Rumble In The Jungle!
 * 
 *  @author: yg@wareninja.com
 *  @see https://github.com/WareNinja
 *  disclaimer: I code for fun, dunno what I'm coding about :-)
 */

package com.wareninja.opensource.strtotime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

class DaysMatcher implements Matcher {

	private final Pattern days = Pattern.compile("[\\-\\+]?\\d+ days");

	public Date tryConvert(String input, String refDateStr) {
		if (refDateStr.isEmpty()) {
			return null;
		}
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.setTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(refDateStr));
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		if (days.matcher(input).matches()) {
			int d = Integer.parseInt(input.split(" ")[0]);
			// Calendar calendar = Calendar.getInstance();
			calendar.add(Calendar.DAY_OF_YEAR, d);
			return calendar.getTime();
		}

		return null;
	}
}
