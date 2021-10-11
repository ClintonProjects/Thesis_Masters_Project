package com.example.controllors;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.ExampleObject;
import com.example.services.ServicesExample;

@RestController
@RequestMapping("/apis")
public class EndPoints {

	@Autowired
	ServicesExample servicesExample;
	
	// http://localhost:8080/apis/getTest
	@GetMapping("/getTest")
	public List<ExampleObject> getAll() {
		return servicesExample.getAll();
	}

	@PostMapping("/save")
	public void saveTest() {
		servicesExample.test();
	}

}
