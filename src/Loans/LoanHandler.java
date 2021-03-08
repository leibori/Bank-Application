package Loans;

/**
 *  The Loan Handler class gives us the opportunity to handle the loans request according to the loan limit .
 *  We are using the Chain of Responsibility pattern by use interface with authorize function and function to get the next object who will
 *  handel the request.
 *
 */
public interface LoanHandler {
    public boolean authorize(Loan request)throws Exception ;
    public LoanHandler getNextHandler() throws Exception;
    public void setNextHandler(LoanHandler handler);

}
