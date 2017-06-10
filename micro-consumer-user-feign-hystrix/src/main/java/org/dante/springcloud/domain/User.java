package org.dante.springcloud.domain;

import java.math.BigDecimal;

public class User {
	private Long id;
	private String account;
	private String name;
	private int age;
	private BigDecimal balance;

	public User() {
	}
	
	public User(Long id) {
		super();
		this.id = id;
	}

	public User(Long id, String account, String name, int age) {
		super();
		this.id = id;
		this.account = account;
		this.name = name;
		this.age = age;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

}
