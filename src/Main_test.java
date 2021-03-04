import Accounts.Account;
import Accounts.CreateAccount;
import Cards.Card;
import Cards.CardFactory;
import Loans.Loan;
import Loans.LoanFactory;

//import static org.junit.Assert.assertEquals;

public class Main_test {
    public static void main(String[] args) throws Exception {
        /*Account ac = CreateAccount.createAccount("testy test", "Basic");
        ac.deposit(25);
        assertEquals(25, ac.getBalance(), 0.0000000001);*/

        /*Account ac2 = CreateAccount.createAccount("testy test", "Recurring Deposit");
        ac2.deposit(50);
        ac2.withdraw(25);
        System.out.println(String.valueOf(ac2.getBalance()));*/
        //assertEquals(23, ac.getBalance(), 0.00000001);
        CardFactory CR = new CardFactory();
        Card c = CR.createCard("Visa","Ori Leibovitz");
        System.out.println(c.toString());
        //assertEquals("amount loaned out is incorrect", 100, lo.getAmount(), 0.0000001);
        //assertEquals("description is incorrect", "test", lR.toString());
    }
}
