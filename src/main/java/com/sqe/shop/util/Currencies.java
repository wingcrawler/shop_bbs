package com.sqe.shop.util;

public enum Currencies {

	RMB("rmb", "RMB￥"), CAD("cad", "CAD$"), USD("usd", "USD$");

	private String keys;

	private String value;

	public String getKeys() {
		return keys;
	}

	public void setKeys(String keys) {
		this.keys = keys;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public static String check(String value) { // 手写的从int到enum的转换函数
		switch (value) {
		case "rmb":
			return RMB.getValue();
		case "cad":
			return CAD.getValue();
		case "usd":
			return USD.getValue();
		default:
			return null;
		}
	}

	Currencies(String keys, String value) {
		this.keys = keys;
		this.value = value;
	}

}
