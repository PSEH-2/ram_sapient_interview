package com.ram.weatherservice.controller;

import java.text.ParseException;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ram.weatherservice.service.WeatherService;

@RestController
public class WeatherReportController {

	@Autowired
	WeatherService weatherService;
	
	@GetMapping("/ping")
	public String getPing() {
		return "Hello Ram";
	}
	
	@GetMapping(value="/weather",produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public String getWeatherReport() throws ParseException {
		return weatherService.getWeatherReport().toString();
	}
}
