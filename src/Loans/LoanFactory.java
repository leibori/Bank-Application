package Loans;

public class LoanFactory {
    public Loan getLoan(String loanType, String description,double loanAmount ) {

        switch (loanType) {
            case "Home":
                return new RegularLoan(description,loanAmount);
            case "Student":
                return new HomeLoan(description,loanAmount);
            case "Regular":
                return new StudentLoan(description,loanAmount);

            default:
                return null;
        }
    }

}
