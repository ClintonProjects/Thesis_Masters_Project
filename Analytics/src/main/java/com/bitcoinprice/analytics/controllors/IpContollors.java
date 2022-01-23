package com.bitcoinprice.analytics.controllors;

import java.net.UnknownHostException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitcoinprice.analytics.services.IpService;
import com.google.gson.Gson;

@RestController
@RequestMapping("/AnalyticsService")
@CrossOrigin(origins = "*")
public class IpContollors {

	@Autowired
	IpService IpService;

	// http://localhost:8080/AnalyticsService/getTotalViews
	@GetMapping("/getTotalViews")
	public int getAll() {
		return IpService.getTotalViewers();
	}
	
	@GetMapping("/getAllUniqueCountryVisted")
	public Map<String, Integer> getAllUniqueCountryVisted() {
		return IpService.getAllUniqueCountryVisted();
	}
	
	@GetMapping("/getAllCountryVisted")
	public Map<String, Integer> getViewsFromCountry() {
		return IpService.getAllCountryVisted();
	}
	
	// http://localhost:8080/AnalyticsService/getUniqueViews
	@GetMapping("/getUniqueViews")
	public int getUniqueviews() {
		return IpService.getUniqueViewers();
	}
	
	// http://localhost:8080/AnalyticsService/UserVisitedTableStore
	@PostMapping("/AnalyticsData")
	public void saveTest(@RequestBody String ips) throws UnknownHostException {
		Map<String, String> jsonJavaRootObject = new Gson().fromJson(ips, Map.class);
			IpService.saveIp(jsonJavaRootObject);
	}
}
