package edu.gatech.cs2340.evergreen;

public class Account {

	private String accountName;
	private String displayName;
	private double balance;
	private double interestRate;
	
	public Account(String accountName, String displayName, double balance, double interestRate) {
		this.accountName = accountName;
		this.displayName = displayName;
		this.balance = balance;
		this.interestRate = interestRate;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	
	
}
