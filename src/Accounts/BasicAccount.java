package Accounts;
public class BasicAccount extends Account{


    public BasicAccount(String ownerName) {
        super(ownerName);
    }

    public BasicAccount(String ownerName, double balance) {
        super(ownerName, balance);
    }

    @Override
    public String getType() {
        return "Basic";
    }

    @Override
    public void setRevenue() {
        //In basic account we do not have revenue
        this.revenue = 0;
    }

    @Override
    public void isDepositRevenue() {
        this.d_revenue =  false;
    }

    @Override
    public void isWithdrawRevenue() {
        this.w_revenue =  false;
    }
}
