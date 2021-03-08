package Loans;
/**
 *  The loan factory class gives the client the opportunity to have 3 types of loan requests.
 *  The factory get the loan type ,description and the loan amount and create the desire loan requests.
 *
 */
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
