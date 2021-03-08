package Transactions;

import Accounts.Account;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Make transaction between two accounts/
 */
public class Transaction {
    private static final DateFormat
            df = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

    private final Account sendingAccount;
    private final Account receivingAccount;

    private final double amount;
    private Date date;

    private boolean
            executed = false;

    public Transaction(Account sendingAccount, Account receivingAccount, double amount) {
        this.sendingAccount = sendingAccount;
        this.receivingAccount = receivingAccount;
        this.amount = amount;
    }

    public boolean wasExecuted() {
        return executed;
    }

    public void execute() throws Exception {
        if(executed)
            throw new Exception("This transaction has already been executed");

        try {
            sendingAccount.withdraw(amount);
        } catch(Exception e) {
            throw new Exception("Sending account was unable to complete transaction");
        }

        try {
            receivingAccount.deposit(amount);
        } catch(Exception e) {
            sendingAccount.deposit(amount);

            throw new Exception("Receiving account was unable to complete transaction");
        }
        this.date = Calendar.getInstance().getTime(); ;

        executed = true;
    }

    @Override
    public String toString() {
        String output =
                "[Transaction]\n" +
                        "Sending account#: " + sendingAccount.getAccountNumber() + "\n" +
                        "Receiving account#: " + receivingAccount.getAccountNumber() + "\n" +
                        "Amount: $" + amount + "\n";

        if(wasExecuted())
            output += "Executed at " + df.format(date) + "\n";
        else
            output += "Not executed\n";

        output += "[End of transaction]";

        return output;
    }
}
