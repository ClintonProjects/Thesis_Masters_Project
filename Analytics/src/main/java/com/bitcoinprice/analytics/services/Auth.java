package com.bitcoinprice.analytics.services;

import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;
import com.bitcoinprice.dataparsing.user.Login;
import com.bitcoinprice.dataparsing.user.UserSession;
import com.bitcoinprice.analytics.repository.SessionIdTable;
import com.bitcoinprice.analytics.repository.UserLoginTable;

@Service
@ComponentScan({ "com.bitcoinprice", "com.bitcoinprice.repository" })
public class Auth {

	@Autowired
	private UserLoginTable UserLoginTable;

	@Autowired
	SessionIdTable sessionIdTable;

	public boolean AuthUserBySessionId(String userId) {
		UserSession userSession = sessionIdTable.findById(userId).orElse(null);
		System.out.println("userSession: " + userSession);
		if (userSession == null)
			return false;
		Login user = UserLoginTable.findBy_id(userSession.getUserId());
		if (!user.isUserAdmin())
			return true;
		else
			return false;
	}

}
