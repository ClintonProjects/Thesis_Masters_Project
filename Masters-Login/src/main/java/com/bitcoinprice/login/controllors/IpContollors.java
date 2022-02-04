package com.bitcoinprice.login.controllors;

import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bitcoinprice.dataparsing.user.Login;
import com.bitcoinprice.login.services.UserLoginService;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class IpContollors {

	@Autowired
	UserLoginService userLoginService;

	@PostMapping(value = "/login")
	public response Login(@RequestBody Login login)
			throws InvalidKeyException, SignatureException, NoSuchAlgorithmException {
		return new response(userLoginService.Login(login.getEmail(), login.getPassword()));
	}

	@PostMapping(value = "/loggedInAs")
	public String loggedInAs(@RequestBody ObjectId SesionId)
			throws InvalidKeyException, SignatureException, NoSuchAlgorithmException {
		return userLoginService.getEmailBySessionId(SesionId);
	}

	@PostMapping(value = "/Register")
	public String RegisterUser(String email, String password)
			throws InvalidKeyException, SignatureException, NoSuchAlgorithmException {
		// System.out.println(email + " " + password);
		// return userLoginService.RegisterUser(email, password);
		return "wow";
	}
	
	
	@GetMapping("/session/{id}")
	@ResponseBody
	public response getFooById(@PathVariable String id) {
	    return new response(userLoginService.getSessionActive(id));
	}
	
	
	
}

class response {
	String id = "";
	boolean session = false;

	response(String id) {
		this.id = id;
	}
	
	response(boolean session) {
		this.session = session;
	}
	
	

	public boolean isSession() {
		return session;
	}

	public void setSession(boolean session) {
		this.session = session;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
