package edu.gatech.cs2340.evergreen;

public class Transaction {

	private String name;
	private double amount;
	private String type;
	
	public Transaction(String type, String name, double amount) {
		this.name = name;
		this.type = type;
		this.amount = amount;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
