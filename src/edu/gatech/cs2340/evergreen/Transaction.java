package edu.gatech.cs2340.evergreen;

import java.sql.Date;
import java.util.Calendar;

public class Transaction {

	private String name;
	private double amount;
	private String type;
	private Date date;
	private String category;
	
	public Transaction(String type, String name, double amount, Date date, String category) {
		this.name = name;
		this.type = type;
		this.amount = amount;
		this.date = date;
		this.category = category;
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
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String Category) {
		this.category = category;
	}
	
}
