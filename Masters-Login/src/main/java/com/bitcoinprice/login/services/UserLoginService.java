package com.bitcoinprice.login.services;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.time.LocalDateTime;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.bitcoinprice.login.repository.SessionIdTable;
import com.bitcoinprice.dataparsing.encryption.Encryption;
import com.bitcoinprice.dataparsing.user.Login;
import com.bitcoinprice.dataparsing.user.UserSession;
import com.bitcoinprice.login.repository.UserLoginTable;

@Service
@ComponentScan({ "com.bitcoinprice", "com.bitcoinprice.repository" })
public class UserLoginService {

	@Autowired
	private UserLoginTable UserLoginTable;

	@Autowired
	private SessionIdTable SessionIdTable;

	public String Login(String email, String password)
			throws InvalidKeyException, SignatureException, NoSuchAlgorithmException {
		Encryption encrypt = new Encryption(password);
		System.out.println(password);
		encrypt.sha256();
		encrypt.hmac256calculateHMAC();
		password = encrypt.getStringToEncrypt();

		Login User = UserLoginTable.findByemail(email);

		if (User != null && password.equals(User.getPassword()) && email.equalsIgnoreCase(email)) {
//			System.out.println("Login Worked");
			UserSession createUserSession = new UserSession(User.get_id());
			SessionIdTable.insert(createUserSession);
			return createUserSession.get_id().toString();
		} else {
//			System.out.println("user found");
			return "";
		}
	}

	@Scheduled(cron = "0 1 1 * * ?")
	@Bean
	public void updateActiveSessions() {
		List<UserSession> createUserSession = SessionIdTable.findAll();
		for (UserSession i : createUserSession) {
			i.updateActiveSession();
			SessionIdTable.save(i);
		}
	}

	public String RegisterUser(String email, String password)
			throws InvalidKeyException, SignatureException, NoSuchAlgorithmException {
		Encryption encrypt = new Encryption(password);
		encrypt.sha256();
		encrypt.hmac256calculateHMAC();
		String encrypPassword = encrypt.getStringToEncrypt();

		if (UserLoginTable.findByemail(email) == null) {
			Login login = new Login(email, encrypPassword);
			UserLoginTable.insert(login);
			return Login(email, password);
		} else {
			System.out.println("user found");
			return "";
		}
	}

	public String getEmailBySessionId(ObjectId sessionIdObj) {
		List<UserSession> createUserSession = SessionIdTable.findAll();
		for (UserSession i : createUserSession) {
			if (i.get_id().equals(sessionIdObj)) {
				System.out.println("Match");

			}
		}
		return null;
	}

	public boolean getSessionActive(String sessionIdObj) {
		ObjectId objId = new ObjectId(sessionIdObj);
		UserSession createUserSession = SessionIdTable.findBy_id(objId);
		if (createUserSession != null)
			return createUserSession.isActiveSession();
		else
			return false;
	}

}
