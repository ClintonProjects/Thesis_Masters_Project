package com.example.websockettest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.services.ServicesExample;

@RestController
public class MyController {

	@MessageMapping("/hello")
	@SendTo("/endpoint/greeting")
	public String getCurrentLocation() {
		System.out.println("connected");
		return "Connected confirmed from server side";
	}

	@Autowired
	private SimpMessagingTemplate template;

	@Autowired
	private ServicesExample servicesExample;

//	@Scheduled(fixedRate = 100)
//	public void scheduledUpdate() {
//		System.out.println("scheduled");
//		servicesExample.checkForNewEntries();
//		this.template.convertAndSend("/endpoint/greeting", ServicesExample.previousExchangeDataRecieved);
//	}

}