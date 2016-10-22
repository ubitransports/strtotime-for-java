package com.wareninja.opensource.strtotime;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import junit.framework.TestCase;

public class Str2TimeTest extends TestCase{
	
	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}

	public void test_lib() {
        Date refDateDate = new Date(1476568800000L); //October 16th, 2016 00:00 AM
        SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String refDate = formater.format(refDateDate);
        Assert.assertEquals(1476568800000L, refDateDate.getTime());
        Assert.assertEquals(1476568800000L + 60 * 1000, Str2Time.convert("1 minutes", refDate).getTime());
        Assert.assertEquals(1476568800000L + 15 * 60 * 1000, Str2Time.convert("15 minutes", refDate).getTime());
        Assert.assertEquals(1476568800000L + 30 * 60 * 1000, Str2Time.convert("30 minutes", refDate).getTime());
        Assert.assertEquals(1476568800000L + 45 * 60 * 1000, Str2Time.convert("45 minutes", refDate).getTime());
        Assert.assertEquals(1476568800000L + 60 * 60 * 1000, Str2Time.convert("1 hour", refDate).getTime());
        Assert.assertEquals(1476568800000L + (60 * 60 * 1000) + (10 * 60 * 1000), Str2Time.convert("+1 hour +10 minutes", refDate).getTime());
        Assert.assertEquals(1476568800000L + (2 * 60 * 60 * 1000), Str2Time.convert("2 hour", refDate).getTime());
        Assert.assertEquals(1476568800000L + (2 * 60 * 60 * 1000) + (30 * 60 * 1000), Str2Time.convert("+2 hour +30 minutes", refDate).getTime());
        Assert.assertEquals(1476568800000L + (3 * 60 * 60 * 1000), Str2Time.convert("3 hour", refDate).getTime());
        Assert.assertEquals(1476655199000L, Str2Time.convert("23:59:59", refDate).getTime());
        Assert.assertEquals(1476611999000L, Str2Time.convert("11:59:59", refDate).getTime());
        Assert.assertEquals(1476568800000L, Str2Time.convert("00:00", refDate).getTime());
        Assert.assertEquals(1477173600000L, Str2Time.convert("+1 week", refDate).getTime());
        Assert.assertEquals(1476741599000L, Str2Time.convert("next monday 23:59:59", refDate).getTime());
        Assert.assertEquals(1479250800000L, Str2Time.convert("1 month", refDate).getTime());
        Assert.assertEquals(1477954799000L, Str2Time.convert("last day of this month 23:59:59", refDate).getTime());
        Assert.assertEquals(1484521200000L, Str2Time.convert("3 month", refDate).getTime());
        Assert.assertEquals(1483225199000L, Str2Time.convert("last day of +2 month 23:59:59", refDate).getTime());
        Assert.assertEquals(1492293600000L, Str2Time.convert("6 month", refDate).getTime());
        Assert.assertEquals(1500156000000L, Str2Time.convert("9 month", refDate).getTime());
        Assert.assertEquals(1508104800000L, Str2Time.convert("1 year", refDate).getTime());
	}
}
