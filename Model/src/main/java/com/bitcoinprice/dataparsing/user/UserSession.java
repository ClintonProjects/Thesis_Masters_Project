package com.bitcoinprice.dataparsing.user;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import javax.persistence.Column;
import javax.persistence.Id;

import org.bson.types.ObjectId;

public class UserSession {

	@Id
	private ObjectId _id;
	private ObjectId userId;
	private Date time;
	private boolean activeSession;

	public UserSession() {
	}

	public UserSession(ObjectId userId) {
		super();
		this.userId = userId;
		setTime();
		updateActiveSession();
	}

	public UserSession(ObjectId _id, ObjectId userId) {
		super();
		this._id = _id;
		this.userId = userId;
	}

	public ObjectId get_id() {
		return _id;
	}

	public void set_id(ObjectId _id) {
		this._id = _id;
	}

	public ObjectId getUserId() {
		return userId;
	}

	public void setUserId(ObjectId userId) {
		this.userId = userId;
	}

	public void setTime() {
		Calendar calendar = Calendar.getInstance();
		time = calendar.getTime();
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public void setActiveSession(boolean activeSession) {
		this.activeSession = activeSession;
	}
	
	

	public boolean isActiveSession() {
		return activeSession;
	}

	public void updateActiveSession() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1);
		Date curretTimeAddOneMonth = cal.getTime();
		if (time.before(curretTimeAddOneMonth)) {
			activeSession = true;
		} else {
			activeSession = false;
		}
	}

}
