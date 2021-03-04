package Loans;

public interface LoanHandler {
    public boolean authorize(Loan request)throws Exception ;
    public LoanHandler getNextHandler() throws Exception;
    public void setNextHandler(LoanHandler handler);

}
