package com.bitcoinprice.endpoints.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bitcoinprice.endpoints.model.ExampleObject;

@Repository
public interface ExampleRepository  extends MongoRepository<ExampleObject, String> {
//	  examples:
//	  public Sample findByFirstName(String firstName);
//	  public List<Sample> findByLastName(String lastName);
	
}