package com.ram.weatherservice.service;

import java.text.ParseException;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ram.weatherservice.util.WeatherUtil;

@Service
public class WeatherService {

	@Autowired
	RestTemplate restClient;
	
	private final String weatherUrl = "http://api.openweathermap.org/data/2.5/forecast?q=London,us&mode=xml&appid=d2929e9483efc82c82c32ee7e02d563e"; 
	
	public JSONArray getWeatherReport() throws ParseException {
		String xmlData = restClient.getForObject(weatherUrl, String.class);
		
		JSONObject jsonObject=WeatherUtil.XmlToJsonString(xmlData);
		JSONArray timeArr=parseJson(jsonObject);
		return timeArr;
	}
	
	private JSONArray parseJson(JSONObject jsonObject) throws ParseException {
		JSONObject weatherData = (JSONObject) jsonObject.get("weatherdata");
		JSONObject timeObj = (JSONObject) weatherData.get("forecast");
		JSONArray localArr = (JSONArray) timeObj.get("time");
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(WeatherConstants.DATE_PATTERN);
		for(int i=0;i<localArr.length();i++){
			JSONObject json_data = localArr.getJSONObject(i);
			
			int diff =WeatherUtil.compareDates(json_data.getString("from"),json_data.getString("to"));
			if(diff <= 3) {
				JSONObject symbol = (JSONObject) json_data.get("symbol");
				JSONObject temperature = (JSONObject) json_data.get("temperature");
				String symbolName = symbol.getString("name");
				Double tempMax = temperature.getDouble("max");
					
				if(symbolName.contains("rain")) {
					json_data.put("alert", "Carry Umbrella");
				}else if(tempMax >= 313.15) {
					json_data.put("alert", "Use sunscreen lotion");
				}
			}
		}
		return (JSONArray) timeObj.get("time");
	}
	
}
