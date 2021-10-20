package com.bitcoinprice.endpoints.controllors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitcoinprice.dataparsing.exchanges.AllDataList;
import com.bitcoinprice.dataparsing.exchanges.ApiExchangeToData;
import com.bitcoinprice.endpoints.services.ServicesExample;

@RestController
@RequestMapping("/apis")
public class EndPoints {

	@Autowired
	ServicesExample servicesExample;
	
	// http://localhost:8080/apis/getTest
	@GetMapping("/getTest")
	public ArrayList<ApiExchangeToData> getAll() throws JSONException, IOException {
		return new AllDataList().getDataList();
	}

	@PostMapping("/save")
	public void saveTest() {
		servicesExample.test();
	}

}
