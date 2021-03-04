package Loans;

public class StudentLoan extends Loan {
    public StudentLoan(String d, double amount) {
        super(d, amount);
    }

    @Override
    double getInterestRate() {
        return 100;
    }
}
