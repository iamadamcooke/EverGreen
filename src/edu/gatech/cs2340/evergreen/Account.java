package edu.gatech.cs2340.evergreen;

// TODO: Auto-generated Javadoc
/**
 * The Class Account.
 */
public class Account {

    /** The account name. */
    private String accountName;

    /** The display name. */
    private String displayName;

    /** The balance. */
    private double balance;

    /** The interest rate. */
    private double interestRate;

    /**
     * Instantiates a new account.
     * 
     * @param accountName
     *            the account name
     * @param displayName
     *            the display name
     * @param balance
     *            the balance
     * @param interestRate
     *            the interest rate
     */
    public Account(String accountName, String displayName, double balance,
        double interestRate) {
        this.accountName = accountName;
        this.displayName = displayName;
        this.balance = balance;
        this.interestRate = interestRate;
    }

    /**
     * Gets the account name.
     * 
     * @return the account name
     */
    public String getAccountName() {
        return accountName;
    }

    /**
     * Sets the account name.
     * 
     * @param accountName
     *            the new account name
     */
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    /**
     * Gets the display name.
     * 
     * @return the display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Sets the display name.
     * 
     * @param displayName
     *            the new display name
     */
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Gets the balance.
     * 
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Sets the balance.
     * 
     * @param balance
     *            the new balance
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * Gets the interest rate.
     * 
     * @return the interest rate
     */
    public double getInterestRate() {
        return interestRate;
    }

    /**
     * Sets the interest rate.
     * 
     * @param interestRate
     *            the new interest rate
     */
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

}
