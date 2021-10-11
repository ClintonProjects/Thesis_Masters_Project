package com.example.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.model.ExampleObject;

@Repository
public interface ExampleRepository  extends MongoRepository<ExampleObject, String> {
//	  examples:
//	  public Sample findByFirstName(String firstName);
//	  public List<Sample> findByLastName(String lastName);
	
}