package com.agodademo.model;

import java.util.Date;

/**
 * @author Isuru Jayakantha
 *
 */
public class KeyModel {

	private String uuid;
	private Date date;
	private int callCount;
	private boolean isSuspended;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getCallCount() {
		return callCount;
	}

	public void setCallCount(int callCount) {
		this.callCount = callCount;
	}

	public boolean isSuspended() {
		return isSuspended;
	}

	public void setSuspended(boolean isSuspended) {
		this.isSuspended = isSuspended;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

}
