package com.jianma.zeus.model;

public class ResultModel {
	
	private String message;
	private int resultCode;
	private Object object;
	private boolean success;
	private int countData;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getResultCode() {
		return resultCode;
	}
	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	public int getCountData() {
		return countData;
	}
	public void setCountData(int countData) {
		this.countData = countData;
	}
	
	
}
