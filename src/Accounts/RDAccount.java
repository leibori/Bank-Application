package Accounts;

public class RDAccount extends Account {
    public RDAccount(String ownerName) {
        super(ownerName);
    }

    public RDAccount(String ownerName, double balance) {
        super(ownerName, balance);
    }

    @Override
    public String getType() {
        return "Recurring Deposit";
    }

    @Override
    public void setRevenue() {
        this.revenue = 0.5;
    }

    @Override
    public void isDepositRevenue() {
        this.d_revenue =  true;
    }

    @Override
    public void isWithdrawRevenue() {
        this.w_revenue =  false;
    }


}
