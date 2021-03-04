package Accounts;

public class CreateAccount {
/**
 * Using the Singleton pattern
 *
 */
    public static Account makeAccount(String accountOwnerName, String accountType)throws Exception{
        Account account;
        if (accountType.equalsIgnoreCase("Basic")) {
            account = new BasicAccount(accountOwnerName);
            System.out.println("A Basic account has been created.");
        }
        else if  (accountType.equalsIgnoreCase("Save")) {
            account = new SaveAccount(accountOwnerName);
            System.out.println("A Save account has been created.");
        }
        else if  (accountType.equalsIgnoreCase("Recurring Deposit")) {
            account = new RDAccount(accountOwnerName);
            System.out.println("A Recurring Deposit account has been created.");
        }
        else
            throw new Exception("Invalid type of account provided");
        return account;
    }
}
