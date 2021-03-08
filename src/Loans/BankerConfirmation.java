package Loans;

public class BankerConfirmation implements LoanHandler {
    private LoanHandler nextHandler;

    /**
     * Give an answer of yes or no about authorizing the request by the limit
     * If not, move to the next authorize persona (the handler)
     * @param request the loan request
     * @return boolean if authorize or not
     * @throws Exception
     */
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

    /**
     * Get the next authorize persona
     * @return LoanHandler persona
     * @throws Exception
     */
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
