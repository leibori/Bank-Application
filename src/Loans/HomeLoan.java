package Loans;

public class HomeLoan extends Loan  {

    public HomeLoan(String d, double amount) {
        super(d, amount);
    }

    @Override
    double getInterestRate() {
        return 0;
    }
}
