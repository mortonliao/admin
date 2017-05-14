package com.longhum.admin.domain;

import java.io.Serializable;

public class ResponseResult implements Serializable{
	private static final long serialVersionUID = 900402794957373941L;
	private int code;
	private String msg;
	private Object data;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "ResponseResult [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}
	public ResponseResult(int code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	public ResponseResult() {
		super();
	}
	
}
