package Accounts;

import NoticeSystem.Visitor;

public class SaveAccount extends Account{

    public SaveAccount(String ownerName) {
        super(ownerName);
    }

    public SaveAccount(String ownerName, double balance) {
        super(ownerName, balance);
    }

    @Override
    public String getType() {
        return "Save";
    }

    @Override
    public void setRevenue() {
        this.revenue = 6.5;
    }

    @Override
    public void isDepositRevenue() {
        this.d_revenue =  false;
    }

    @Override
    public void isWithdrawRevenue() {
        this.w_revenue =  true;
    }
}
