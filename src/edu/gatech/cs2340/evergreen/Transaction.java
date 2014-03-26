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
		try{
			this.name = name;
			this.type = type;
			this.amount = amount;
			this.date = date;
			this.category = category;
		}
		catch (IllegalArgumentException e){
			
		}
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		try{
			this.name = name;
		}
		catch (IllegalArgumentException e)
		{
			
		}
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		try{
			this.amount = amount;
		}
		catch (IllegalArgumentException e)
		{
			
		}
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		try{
			this.type = type;
		}
		catch (IllegalArgumentException e)
		{
			
		}
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		try{
			this.date = date;
		}
		catch (IllegalArgumentException e)
		{
			
		}
	}
	
	public String getCategory() {
		return category;
	}
	
	public void setCategory(String Category) {
		try{
			this.category = Category;
		}
		catch (IllegalArgumentException e)
		{
			
		}
	}
	
}
