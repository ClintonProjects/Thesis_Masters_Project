package com.example.services;

import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import com.example.model.ExampleObject;
import com.example.repository.ExampleRepository;

@Service
@ComponentScan({ "com.example", "com.example.repository" })
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
