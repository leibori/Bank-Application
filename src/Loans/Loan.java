package Loans;

import java.io.Serializable;
/**
 *  Loan class gives the client the opportunity to get loan from the bank with interest rate.
 *  We are using the Factory pattern by implementing factory class which make 3 types of bank loans.
 *  Also we implementing the Chain of Responsibility pattern (this is the reason we are implements serializable).
 *
 */
public abstract class Loan implements Serializable {

    private String description;
    private double loanAmount;

    /**
     * Constructor
     * @param d description
     * @param amount the amount of money the client ask
     */
    public Loan(String d, double amount) {
        description = d;
        loanAmount = amount;
    }
    // Abstract method
    abstract double getInterestRate();

    public double getAmount() {
        return loanAmount;
    }
    public String toString() {
        return  description;
    }
    public double calculateInterest(){
        return getAmount() - (getInterestRate() / 10);
    }

}
