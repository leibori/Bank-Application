package Loans;

public class BankerConfirmation implements LoanHandler {
    private LoanHandler nextHandler;

    @Override
    public boolean authorize(Loan request) throws Exception {
        double amount = request.getAmount();
        if (amount <= 7500) {
            System.out.println("A banker has authorized the loan request of " + request.getAmount() +
                    "and the amount that will deposit is: "+ request.calculateInterest());
            return true;
        } else {
            return getNextHandler().authorize(request);
        }    }

    @Override
    public LoanHandler getNextHandler() throws Exception{
        if(nextHandler == null){
            throw new Exception("No bank employee could authorize the loan request");
        }
        return nextHandler;
    }

    @Override
    public void setNextHandler(LoanHandler handler) {
        nextHandler = handler;
    }
}
