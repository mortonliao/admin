package com.longhum.admin.entity;

import java.io.Serializable;

public class ResultSimpleDate implements Serializable{
	private static final long serialVersionUID = -2417674064975906261L;
	private int code;
	private String msg;
	private Object data;
	
	public ResultSimpleDate(int code, String msg) {
		super();
		this.code = code;
		this.msg = msg;
	}
	public ResultSimpleDate() {
		super();
	}
	public ResultSimpleDate(int code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
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
		return "ResultSimpleDate [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}
	public static ResultSimpleDate ok(String msg){
		ResultSimpleDate result = new ResultSimpleDate(1,msg);
		return result;
	}
	public static ResultSimpleDate ok(String msg,Object data){
		ResultSimpleDate result = new ResultSimpleDate(1,msg,data);
		return result;
	}
	public static ResultSimpleDate error(String msg){
		ResultSimpleDate result = new ResultSimpleDate(0,msg);
		return result;
	}
}
