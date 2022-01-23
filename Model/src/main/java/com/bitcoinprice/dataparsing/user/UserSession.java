package com.bitcoinprice.dataparsing.user;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.TimeZone;

import javax.persistence.Id;

import org.bson.types.ObjectId;

public class UserSession {

	@Id
	private ObjectId _id;
	private ObjectId userId;
	private DateTimeFormatter time;

	public UserSession() {
	}

	public UserSession(ObjectId userId) {
		super();
		this.userId = userId;
		setTime();
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

	public DateTimeFormatter getTime() {
		return time;
	}

	public void setTime() {
//		SimpleDateFormat formattedDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
//		formattedDate.setTimeZone(TimeZone.getTimeZone("UTC"));
//		return formattedDate.format(date);
	}

}
