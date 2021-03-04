package WithdrawDeposit;

import Accounts.Account;
import NoticeSystem.Visitor;

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

    public WithdrawDeposit_State transitionState() { return null; }

    public static WithdrawDeposit_State InitialState(Account account) {
        return new NoTransFee(account);
    }
    public void accept(Visitor visitor){};

    public void deposit(double amount) {
        double balance = getContext().getBalance();

        getContext().setBalance(balance + amount);
        transitionState();
    }

    public void withdraw(double amount) throws Exception {
        double balance = getContext().getBalance();

        getContext().setBalance(balance - amount);
        transitionState();
    }

}
