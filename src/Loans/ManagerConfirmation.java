package Loans;

public class ManagerConfirmation implements LoanHandler {
    private LoanHandler nextHandler;
    @Override
    public boolean authorize(Loan request) throws Exception {
        double amount = request.getAmount();
        if (amount <= 50000) {
            System.out.println("A manager has authorized the loan request of " + request.getAmount() + "and the amount that will deposit is: "+ request.calculateInterest());
            return true;
        } else {
            return getNextHandler().authorize(request);
        }
    }


    @Override
    public LoanHandler getNextHandler() throws Exception {
        if(nextHandler == null){
            throw new Exception("No manager could authorize the loan request");
        }
        return nextHandler;       }

    @Override
    public void setNextHandler(LoanHandler handler) {
        nextHandler = handler;
    }
}