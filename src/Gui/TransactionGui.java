package Gui;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;
import Accounts.Account;
import Transactions.Transaction;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TransactionGui {

    private JFrame frame;

    private User
            user;

    private Account
            account;

    private JTextField textField;

    private DefaultListModel
            listModel = new DefaultListModel();

    private JList
            list;

    private JButton
            btnTransfer;

    private ArrayList<Account>
            accounts = new ArrayList<>();

    /**
     * Create the application.
     */
    public TransactionGui(User user, Account account) {
        this.user = user;
        this.account = account;
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 474, 162);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        frame.setTitle("Transfering from account #" + account.getAccountNumber());
        frame.getContentPane().setLayout(new MigLayout("", "[][grow]", "[][grow][]"));

        JLabel lblAmount = new JLabel("Amount");
        frame.getContentPane().add(lblAmount, "cell 0 0,alignx trailing");

        textField = new JTextField();
        frame.getContentPane().add(textField, "cell 1 0,growx");
        textField.setColumns(10);

        JLabel lblTargetAccount = new JLabel("Target account");
        frame.getContentPane().add(lblTargetAccount, "cell 0 1");

        for(User user : Environment.getUsers()) {
            accounts.addAll(user.getAccounts());
        }

        accounts.remove(account);

        Collections.sort(accounts);

        for(Account account : accounts) {
            listModel.addElement(account.getAccountNumber());
        }

        list = new JList<String>(listModel);


        frame.getContentPane().add(list, "cell 1 1,grow");

        btnTransfer = new JButton("Transfer");
        btnTransfer.addActionListener(btnTransferListener);

        frame.getContentPane().add(btnTransfer, "cell 1 2");

        frame.setVisible(true);
    }

    private ActionListener btnTransferListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            if(list.isSelectionEmpty() == false && textField.getText().length() > 0) {
                btnTransfer.setEnabled(false);;

                double amount = Double.parseDouble(textField.getText());
                Account otherAccount = getSelectedAccount();

                Transaction transaction = new Transaction(account, otherAccount, amount);

                try {
                    transaction.execute();
                    JOptionPane.showMessageDialog(frame, "Transaction was completed");
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(frame, "ERROR");
                }
            }
        }
    };

    private Account getSelectedAccount() {
        if(list.isSelectionEmpty() == false) {
            for(Account account : accounts) {
                if(account.getAccountNumber() == (Integer) list.getSelectedValue()) {
                    return account;
                }
            }
        }
        return null;
    };

}
