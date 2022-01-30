package com.bitcoinprice.analytics.controllors;

import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.bitcoinprice.analytics.services.UserRegisterService;
import com.bitcoinprice.dataparsing.user.Login;
import com.bitcoinprice.analytics.services.UserLoginService;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class IpContollors {
	
	@Autowired
	UserLoginService userLoginService;

	@Autowired
	UserRegisterService userRegisterService;

	
//	@PostMapping(value = "/Login")
//	@ResponseBody
//	public void Login(@RequestBody String email, @RequestBody String password) throws InvalidKeyException, SignatureException, NoSuchAlgorithmException {
//		System.out.println(email + " " + password);
////		userLoginService.RegisterUser(email, password);
//	}
	
	// http://localhost:8080/AnalyticsService/UserVisitedTableStore
	@PostMapping("/Login")
	public void saveTest(@RequestBody String email, @RequestBody String password) throws UnknownHostException {
//		System.out.println(email + " " + password);
	}
	
	
	@PostMapping(value = "/Register")
	@ResponseBody
	public String RegisterUser(@RequestParam String email, @RequestParam String password) throws InvalidKeyException, SignatureException, NoSuchAlgorithmException {
		System.out.println(email + " " + password);
		return userRegisterService.RegisterUser(email, password);
	}
}
