package Loans;

public class RegularLoan extends Loan {
    public RegularLoan(String d, double amount) {
        super(d, amount);
    }
    @Override
    double getInterestRate() {
        return 50;
    }
}
