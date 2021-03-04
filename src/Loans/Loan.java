package Loans;

import java.io.Serializable;

public abstract class Loan implements Serializable {

    private String description;
    private double loanAmount;
    abstract double getInterestRate();
    public Loan(String d, double amount) {
        description = d;
        loanAmount = amount;
    }

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
