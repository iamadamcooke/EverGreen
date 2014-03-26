package edu.gatech.cs2340.evergreen;
import java.sql.Date;
import org.junit.Test;
import org.junit.Assert;

public class TransactionTest {
	Transaction trans;
	
	@Test
	public void initTest()
	{
		String date = "2014-12-13";
		String type = "debit";
		String name = "Mike Mekins";
		double amount = 50.00;
		Date d =java.sql.Date.valueOf(date);

		String category = "pleasure";
		trans = new Transaction(type,name,amount, d, category);
		
		Assert.assertEquals("Mike Mekins", trans.getName());
		Assert.assertEquals(50.00,trans.getAmount(),0.1);
		Assert.assertEquals("debit", trans.getType());
		Assert.assertEquals(d, trans.getDate());
		Assert.assertEquals("pleasure", trans.getCategory());
		Assert.assertEquals(d.toString(), trans.getDate().toString());
	}
	@Test
	public void setTest()
	{
		String date = "2014-12-13";
		String type = "debit";
		String name = "Sarah Connor";
		double amount = 50.00;
		Date d =java.sql.Date.valueOf(date);

		String category = "pleasure";
		trans = new Transaction(type,name,amount, d, category);
		
		trans.setAmount(20.00);
		trans.setCategory("buisness");
		date = "2013-12-13";
		d = java.sql.Date.valueOf(date);
		trans.setDate(d);
		trans.setName("Angelina");
		Assert.assertEquals(20.00,trans.getAmount(),0.1);
		Assert.assertEquals("buisness", trans.getCategory());
		Assert.assertEquals(d.toString(),trans.getDate().toString());
		Assert.assertEquals("Angelina", trans.getName());
	}
	
	public void errorTest()
	{
		String date = "2014-14-13";
		int type = 5;
		int name = 9335;
		String amount = "50.00";
		Date d =java.sql.Date.valueOf(date);

		int category = 123;
		trans = new Transaction(type,name,amount, d, category);
	}

}
