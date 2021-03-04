package Accounts;

import Cards.Card;
import NoticeSystem.MessageVisitor;
import NoticeSystem.Visitor;
import WithdrawDeposit.WithdrawDeposit_State;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
/**
 *  Bank account class is the most important class in the app.
 *  We are using the Template pattern by implementing template method which call in the constructor.
 *
 */
public abstract class Account implements Comparable<Account>, Serializable {
    private int accountNumber;
    private boolean accountOpen = true;
    private LinkedList<Card> cards = new LinkedList<Card>();
    private double balance;
    private WithdrawDeposit_State state;
    private String accountOwnerName;
    double revenue;
    // Is there need to deposit revenue
    boolean d_revenue;
    // Is there need to withdraw revenue
    boolean w_revenue;

    public Account(String ownerName) {
        templateMethod(ownerName);
    }
    public Account(String ownerName, double balance)  {
        templateMethod(ownerName);
        setBalance(balance);
    }

    private boolean isAccountOpen() { return accountOpen; }
    public final void closeAccount() { this.accountOpen = false; }
    public final void setAccountOwnerName(String accountOwnerName) { this.accountOwnerName = accountOwnerName; }
    public final void setBalance(double balance) { this.balance = balance; }
    public final int getAccountNumber() { return accountNumber; }
    public final void addCard(Card newCard){ this.cards.add(newCard);}
    public final void setState(WithdrawDeposit_State state) { this.state = state; }
    public final void createAccountNumber(){
        Random rand = new Random();
        this.accountNumber = rand.nextInt(9999-1000);
    }
    final void templateMethod(String ownerName){
        setAccountOwnerName(ownerName);
        createAccountNumber();
        setState(WithdrawDeposit_State.InitialState(this));
        setRevenue();
        isDepositRevenue();
        isWithdrawRevenue();
    }
    public abstract String getType();
    public abstract void setRevenue();
    public abstract void isDepositRevenue();
    public abstract void isWithdrawRevenue();

    public double getBalance() { return balance; }
    public LinkedList<Card> getCards() { return cards; }
    public void deleteCard(Card oldCard){ this.cards.remove(oldCard);}
    public String getAccountOwnerName() { return accountOwnerName; }
    public WithdrawDeposit_State getState() { return state; }




    public  void deposit(double amount) throws Exception {
        if(amount <= 0 )
            throw new Exception("Invalid amount");
        if (d_revenue){
            getState().deposit(amount -((amount/12)*revenue));
        }
        else {
            getState().deposit(amount);
        }
        Visitor messV = new MessageVisitor();
        getState().accept(messV);
    }
    public void withdraw(double amount) throws Exception {
        if(amount <= 0)
            throw new Exception("Invalid amount");
        if (w_revenue){
            getState().withdraw(amount+revenue);
        }
        else {
            getState().withdraw(amount);
        }
        Visitor messV = new MessageVisitor();
        getState().accept(messV);
    }
    @Override
    public String toString() {
        String output =
                "[Account]\n" +
                        "Account#: " + accountNumber + "\n" +
                        "Balance: $" + balance + "\n" +
                        "Owner: " + accountOwnerName + "\n" +
                        "Account status: ";
        if(isAccountOpen())
            output += "Open";
        else
            output += "Closed";
        return output;
    }

    @Override
    public int compareTo(Account o) {
        int comparenumber=((Account)o).getAccountNumber();
        return this.accountNumber-comparenumber;
    }
}
