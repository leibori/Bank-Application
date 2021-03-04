package WithdrawDeposit;

import Accounts.Account;
import NoticeSystem.Visitor;

public class NoTransFee extends WithdrawDeposit_State {

    public NoTransFee(Account account) {
        super(account);
    }

    public NoTransFee(WithdrawDeposit_State source) {
        super(source);
    }

    public void deposit(double amount) {
        super.deposit(amount);
    }

    public void withdraw(double amount) throws Exception {
        double balance = getContext().getBalance();

        if ((balance - amount) <= this.limit_overdraw)
            throw new Exception("Insufficient funds");

        super.withdraw(amount);
    }

    public WithdrawDeposit_State transitionState() {
        double balance = getContext().getBalance();

        if (balance < 0)
            getContext().setState(new OverDrawn(this));
        else if (balance < this.balance_min)
            getContext().setState(new TransFee(this));

        return getContext().getState();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
