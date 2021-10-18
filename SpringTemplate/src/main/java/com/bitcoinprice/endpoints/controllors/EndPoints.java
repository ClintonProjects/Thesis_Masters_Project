package com.bitcoinprice.endpoints.controllors;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bitcoinprice.dataparsing.exchanges.AllDataList;
import com.bitcoinprice.endpoints.model.ExampleObject;
import com.bitcoinprice.endpoints.services.ServicesExample;

@RestController
@RequestMapping("/apis")
public class EndPoints {

	@Autowired
	ServicesExample servicesExample;
	
	// http://localhost:8080/apis/getTest
	@GetMapping("/getTest")
	public List<ExampleObject> getAll() {
		AllDataList allDataList = new AllDataList();
		allDataList.allApiData();
		return null;
	}

	@PostMapping("/save")
	public void saveTest() {
		servicesExample.test();
	}

}
