package com.bitcoinprice.login.controllors;

import java.net.UnknownHostException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.bitcoinprice.login.services.UserRegisterService;

@RestController
@RequestMapping("/login")
@CrossOrigin(origins = "*")
public class IpContollors {

	@Autowired
	UserLoginService userLoginService;

	@PostMapping(value = "/Login")
	public String Login(@RequestParam("email")  String email, @RequestParam("password") String password)
			throws InvalidKeyException, SignatureException, NoSuchAlgorithmException {
		System.out.println(email + " " + password);
		return userLoginService.Login(email, password);
	}

	@PostMapping(value = "/Register")
	@ResponseBody
	 public  String RegisterUser(@RequestBody String email, @RequestBody String password)
			throws InvalidKeyException, SignatureException, NoSuchAlgorithmException {
		System.out.println(email + " " + password);
		return userLoginService.RegisterUser(email, password);
	}
}
