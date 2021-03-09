package WithdrawDeposit;

import Accounts.Account;
import NoticeSystem.Visitor;
/**
 *  The WithdrawDeposit class gives us the opportunity to update easily the account state .
 *  By using the state pattern we can change the state of the account while the code is running.
 */
public abstract class WithdrawDeposit_State {
    private Account context;
    public double limit_overdraw = -1000.00;
    public double balance_min = 0.00;
    public double fee_basic = 5.75;
    public double fee_overdraw = 8.20;

    public WithdrawDeposit_State(Account account) {

        setContext(account);
    }

    public WithdrawDeposit_State(WithdrawDeposit_State source) {

        setContext(source.getContext());
    }

    public Account getContext() {
        return context;
    }

    public void setContext(Account newAccount) {
        context = newAccount;
    }

    /**
     * Initial the state to be the basic one without fee
     * @param account
     * @return state
     */
    public static WithdrawDeposit_State InitialState(Account account) {
        return new NoTransFee(account);
    }

    /**
     * Apply the visitor method by state
     * @param visitor
     */
    public abstract  void accept(Visitor visitor);

    abstract WithdrawDeposit_State transitionState();

    public abstract void deposit(double amount);

    public abstract void withdraw(double amount) throws Exception;

}
