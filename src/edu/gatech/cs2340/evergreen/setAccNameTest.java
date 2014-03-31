/**
 * 
 */
package edu.gatech.cs2340.evergreen;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Ting
 *
 */
public class setAccNameTest {

    Account myAcc;
    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        myAcc = new Account("myAccount", "display", 100, 0.1);
    }

    /**
     * Test method for {@link edu.gatech.cs2340.evergreen.Account#Account(java.lang.String, java.lang.String, double, double)}.
     */
    @Test
    public void testAccount() {
        Assert.assertEquals("myAccount", myAcc.getAccountName());
        Assert.assertEquals("display", myAcc.getDisplayName());
        Assert.assertEquals(100, myAcc.getBalance(), 0.1);
        Assert.assertEquals(0.1, myAcc.getInterestRate(), 0.1);
        
        
    }

    /**
     * Test method for {@link edu.gatech.cs2340.evergreen.Account#getAccountName()}.
     */
    @Test
    public void testGetAccountName() {
        Assert.assertEquals("myAccount", myAcc.getAccountName());
    }

    /**
     * Test method for {@link edu.gatech.cs2340.evergreen.Account#setAccountName(java.lang.String)}.
     */
    @Test
    public void testSetAccountName() {
        myAcc.setAccountName("yourAcc");
        Assert.assertEquals("yourAcc", myAcc.getAccountName());
        
        myAcc.setAccountName("Water's");
        Assert.assertEquals("Water's", myAcc.getAccountName());
        
        myAcc.setAccountName(null);
        Assert.assertEquals("Water's", myAcc.getAccountName());
    }

}
