package com.bitcoinprice.endpoints.services;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.bitcoinprice.endpoints.model.ExampleObject;
import com.bitcoinprice.endpoints.repository.ExampleRepository;

@Service
@ComponentScan({ "com.bitcoinprice.endpoints", "com.bitcoinprice.endpoints.repository" })
public class ServicesExample {

	@Autowired
	private ExampleRepository exampleRepository;

	public void test() {
		ExampleObject exampleObject = new ExampleObject();
		
		exampleObject.set_id(new ObjectId());
		exampleRepository.save(exampleObject);
	}

	public List<ExampleObject> getAll() {
		return exampleRepository.findAll();
	}

}
