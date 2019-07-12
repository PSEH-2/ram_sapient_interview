package com.ram.weatherservice.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import com.ram.weatherservice.constant.WeatherConstants;

public class WeatherUtil {

	public static JSONObject XmlToJsonString(String xmlString) {

		JSONObject jsonObject=null;
		try {
			jsonObject = XML.toJSONObject(xmlString);
			System.out.println(jsonObject);
		} catch (JSONException je) {
			System.out.println(je.toString());
		}
		return jsonObject;
	}
	
	public static Date getDateForString(String dateStr) throws ParseException {
		return new SimpleDateFormat("yyyy-dd-mm").parse(dateStr);
	}
	
	public static int compareDates(String inputString1,String inputString2)
    {

		String pattern = WeatherConstants.DATE_PATTERN;
		int diffDays = 0;
		SimpleDateFormat myFormat = new SimpleDateFormat(pattern);
		try {
		    Date date1 = myFormat.parse(inputString1);
		    Date date2 = myFormat.parse(inputString2);
		    long diff = date2.getTime() - date1.getTime();
		    diffDays = (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		return diffDays;
    }
}
