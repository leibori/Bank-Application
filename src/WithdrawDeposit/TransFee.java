package WithdrawDeposit;

import Accounts.Account;
import NoticeSystem.Visitor;

public class TransFee extends WithdrawDeposit_State {

    public TransFee(Account account) {
        super(account);
    }

    public TransFee(WithdrawDeposit_State source) {
        super(source);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public WithdrawDeposit_State transitionState() {
        double balance = getContext().getBalance();

        if (balance < 0)
            getContext().setState(new OverDrawn(this));

        else if (balance >= this.balance_min)
            getContext().setState(new NoTransFee(this));

        return getContext().getState();
    }

    public void deposit(double amount) {
        double balance = getContext().getBalance();

        getContext().setBalance(balance - this.fee_basic);
        System.out.println("A transaction fee of $" + this.fee_basic + " was charged due to account status (Less than minimum balance)");
        super.deposit(amount);
    }

    public void withdraw(double amount) throws Exception {
        double balance = getContext().getBalance();

        if ((balance - this.fee_basic - amount) >this.limit_overdraw)
            throw new Exception("Overdraw limit exceeded");

        getContext().setBalance(balance - this.fee_basic);
        System.out.println("A transaction fee of $" + this.fee_basic + " was charged due to account status (Less than minimum balance)");
        super.withdraw(amount);
    }

}
