package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.repository.ExampleRepository;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private ExampleRepository accountRepository;

	@Test
	public void shouldWireRepository() {
		assertNotNull(accountRepository);
	}

}
