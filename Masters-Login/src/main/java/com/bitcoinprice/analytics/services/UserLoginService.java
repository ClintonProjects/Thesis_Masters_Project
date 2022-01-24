package com.bitcoinprice.analytics.services;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.bitcoinprice.analytics.repository.UserLoginTable;
import com.bitcoinprice.dataparsing.encryption.Encryption;
import com.bitcoinprice.dataparsing.user.Login;
import com.bitcoinprice.dataparsing.user.UserSession;

@Service
@ComponentScan({ "com.bitcoinprice", "com.bitcoinprice.repository" })
public class UserLoginService {
	
	@Autowired
	private UserLoginTable Repository;
	
	@Autowired
	private com.bitcoinprice.analytics.repository.SessionIdTable SessionIdTable;
	
	public void RegisterUser(String email, String password) throws InvalidKeyException, SignatureException, NoSuchAlgorithmException {
		Encryption encrypt = new Encryption(password);
		System.out.println(password);
		encrypt.sha256();
		encrypt.hmac256calculateHMAC();
		password = encrypt.getStringToEncrypt();
		
		Login User = Repository.findByemail(email);

		if (password.equals(User.getPassword()) && email.equalsIgnoreCase(email) ) {
			System.out.println("Login Worked");
//			UserSession UserSession = new UserSession(User.get_id());
//			SessionIdTable.save(UserSession);
		} else {
			System.out.println("Login Failure");
	;
		}

	}
	
	
	
}
