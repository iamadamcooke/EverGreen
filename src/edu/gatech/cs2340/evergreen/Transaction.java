package edu.gatech.cs2340.evergreen;

import java.sql.Date;
import java.util.Calendar;

// TODO: Auto-generated Javadoc
/**
 * The Class Transaction.
 */
public class Transaction {

	/** The name. */
	private String name;
	
	/** The amount. */
	private double amount;
	
	/** The type. */
	private String type;
	
	/** The date. */
	private Date date;
	
	/** The category. */
	private String category;

	/**
	 * Instantiates a new transaction.
	 *
	 * @param type the type
	 * @param name the name
	 * @param amount the amount
	 * @param date the date
	 * @param category the category
	 */
	public Transaction(String type, String name, double amount, Date date,
			String category) {
		try {
			this.name = name;
			this.type = type;
			this.amount = amount;
			this.date = date;
			this.category = category;
		} catch (IllegalArgumentException e) {

		}
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		try {
			this.name = name;
		} catch (IllegalArgumentException e) {

		}
	}

	/**
	 * Gets the amount.
	 *
	 * @return the amount
	 */
	public double getAmount() {
		return amount;
	}

	/**
	 * Sets the amount.
	 *
	 * @param amount the new amount
	 */
	public void setAmount(double amount) {
		try {
			this.amount = amount;
		} catch (IllegalArgumentException e) {

		}
	}

	/**
	 * Gets the type.
	 *
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 *
	 * @param type the new type
	 */
	public void setType(String type) {
		try {
			this.type = type;
		} catch (IllegalArgumentException e) {

		}
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(Date date) {
		try {
			this.date = date;
		} catch (IllegalArgumentException e) {

		}
	}

	/**
	 * Gets the category.
	 *
	 * @return the category
	 */
	public String getCategory() {
		return category;
	}

	/**
	 * Sets the category.
	 *
	 * @param Category the new category
	 */
	public void setCategory(String Category) {
		try {
			this.category = Category;
		} catch (IllegalArgumentException e) {

		}
	}

}
