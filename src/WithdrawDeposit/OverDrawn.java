package WithdrawDeposit;

import Accounts.Account;
import NoticeSystem.Visitor;

public class OverDrawn extends WithdrawDeposit_State {
    private String alert = "Attention: Your Account is Overdrawn";


    public OverDrawn(Account account) {
        super(account);
    }

    public OverDrawn(WithdrawDeposit_State source) {
        super(source);
    }

    @Override
    WithdrawDeposit_State transitionState() {
        double balance = getContext().getBalance();
        if (balance >= this.balance_min)
            getContext().setState(new NoTransFee(this));
        else if (balance >= 0)
            getContext().setState(new TransFee(this));

        return getContext().getState();
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    @Override
    public void deposit(double amount) {
        double balance = getContext().getBalance();

        getContext().setBalance(balance - this.fee_overdraw);
        alertAccountHolder();
        System.out.println("A transaction fee of $" + this.fee_overdraw+ " was charged due to account status (Overdrawn)");
        getContext().setBalance(balance + amount);
        transitionState();
    }

    @Override
    public void withdraw(double amount) throws Exception {
        double balance = getContext().getBalance();

        if ((balance - this.fee_overdraw -	amount) > this.limit_overdraw)
            throw new Exception("Overdraw limit has been exceeded");

        getContext().setBalance(balance - this.fee_overdraw);
        alertAccountHolder();
        System.out.println("A transaction fee of $" + this.fee_overdraw + " was charged due to account status (Overdrawn)");
        getContext().setBalance(balance - amount);
        transitionState();
    }

    public void alertAccountHolder() {
        System.out.println(alert);
    }
}
