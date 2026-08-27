package com.pers.utils;

public class CustomeExceptionUtil extends Exception {

	public CustomeExceptionUtil(String msg) {
		super(msg);
		System.out.println(msg);
	}

	public void CustElementNotFoundExce(String msg) {
		System.out.println(msg);
	}

	public void CustNotEqualExce(String msg) {
		System.out.println(msg);
	}

}
