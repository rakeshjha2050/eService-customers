package com.eService.customers;

import java.io.Serializable;

public class Account implements Serializable{

	private static final long serialVersionUID = 1L;
	public Account(Long amount, String number, String name) {
		super();
		this.amount = amount;
		this.number = number;
		this.name = name;
	}
	public Account() {
		super();
	}
	private Long amount;
	private String number;
	private String name;
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

}
