package Gui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Loans.*;
import net.miginfocom.swing.MigLayout;
import Accounts.Account;
import Cards.Card;
import Cards.CardFactory;


public class Accounts {

    private JFrame frame;

    private User user;

    private JList list;

    private DefaultListModel listModel;

    private JTextPane txtAccountInfo = new JTextPane();

    /**
     * Create the application.
     */
    public Accounts(User user) {
        this.user = user;
        initialize();

    }

    private void setComponents(User user) {
        ArrayList listTemp = user.getAccounts();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {

        listModel = new DefaultListModel();

        refreshListModel();

        frame = new JFrame();
        frame.setBounds(100, 100, 1000, 800);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(222, 222, 222));

        frame.setTitle(this.user.getUsername() + "'s Home");
        frame.getContentPane().setLayout(new MigLayout("", "[61px,grow][93px,grow][99px]", "[23px][grow]"));

        ImageIcon icon1 = new ImageIcon(this.getClass().getResource("dvirb.png"));
        JLabel back = new JLabel("",icon1, JLabel.RIGHT);
        back.setBounds(250,250,250,250);
        frame.getContentPane().add(back, "cell 6 6");
        back.setOpaque(false);
        //frame.add(back);

        Label label = new Label("Accounts: " );
        //frame.setLocation(0,0);
        frame.getContentPane().add(label, "cell 0 0" );
        label.setFont(new Font("Courier", Font.BOLD,20));

        JButton btnAddAccount = new JButton("Add Account");
        btnAddAccount.setFont(new Font("Courier", Font.BOLD,16));
        btnAddAccount.addActionListener(btnAddAccountListener);
        frame.getContentPane().add(btnAddAccount, "cell 0 3 ,alignx left,aligny top");
        btnAddAccount.setBackground(new Color(252, 164, 122));
        btnAddAccount.setPreferredSize(new Dimension(50, 50));


        JLabel lblAccountInfo = new JLabel("Account Info");
        frame.getContentPane().add(lblAccountInfo, "cell 1 0");
        lblAccountInfo.setFont(new Font("Courier", Font.BOLD,20));

        JLabel lblAccountActions = new JLabel("Account Actions:");
        frame.getContentPane().add(lblAccountActions, "cell 0 4");
        //JTextPane txtAccountInfo;
        lblAccountActions.setFont(new Font("Courier", Font.BOLD,20));

        list = new JList(listModel);
        list.addListSelectionListener(listSelectionListener);
        list.setSize(300,600);
        frame.getContentPane().add(list, "cell 0 0,grow");

        txtAccountInfo.setSize(50,20);
        frame.getContentPane().add(txtAccountInfo, "cell 1 0,grow");

        JButton btnAddCard = new JButton("Add Card");
        btnAddCard.setFont(new Font("Courier", Font.BOLD,16));
        btnAddCard.addActionListener(btnAddCardListener);
        frame.getContentPane().add(btnAddCard, "flowy,cell 0 6");
        //btnAddCard.setLocation(100,100);
        btnAddCard.setBackground(new Color(252, 164, 122));
        btnAddCard.setPreferredSize(new Dimension(50, 50));

        JButton btnWithdraw = new JButton("Withdraw");
        btnWithdraw.setFont(new Font("Courier", Font.BOLD,16));
        btnWithdraw.addActionListener(btnWithdrawListener);
        frame.getContentPane().add(btnWithdraw, "cell 1 5");
        btnWithdraw.setBackground(new Color(252, 164, 122));
        btnWithdraw.setPreferredSize(new Dimension(50, 50));

        JButton btnDeposit = new JButton("Deposit");
        btnDeposit.setFont(new Font("Courier", Font.BOLD,16));
        btnDeposit.addActionListener(btnDepositListener);
        frame.getContentPane().add(btnDeposit, "cell 0 5");
        btnDeposit.setBackground(new Color(252, 164, 122));
        btnDeposit.setPreferredSize(new Dimension(50, 50));

        JButton btnRequestLoan = new JButton("Request Loan");
        btnRequestLoan.setFont(new Font("Courier", Font.BOLD,16));
        btnRequestLoan.addActionListener(btnRequestLoanListener);
        frame.getContentPane().add(btnRequestLoan, "cell 1 6");
        btnRequestLoan.setBackground(new Color(252, 164, 122));
        btnRequestLoan.setPreferredSize(new Dimension(50, 50));

        frame.setVisible(true);


    }


    private ActionListener btnRequestLoanListener = new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            JFrame f;
            JTextField tf1;

            JLabel l1;
            l1 = new JLabel();
            l1.setBounds(20, 25, 350, 30);
            l1.setText("Please specify the amount you want to request a loan for");

            tf1 = new JTextField();
            tf1.setBounds(100, 50, 70, 40);

            f = new JFrame("Loan");
            final JLabel label = new JLabel();
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setSize(400, 100);
            JButton b = new JButton("OK");
            b.setBounds(200, 100, 75, 20);
            String languages[] = {"Home", "Regular", "Student"};
            final JComboBox cb = new JComboBox(languages);
            cb.setBounds(50, 100, 90, 20);
            f.add(l1);
            f.add(tf1);
            f.add(cb);
            f.add(label);
            f.add(b);
            f.setLayout(null);
            f.setSize(350, 350);
            f.setVisible(true);

            b.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String loanType = ""
                            + cb.getItemAt(cb.getSelectedIndex());
                    double amount = Double.parseDouble(tf1.getText());
                    LoanFactory LR = new LoanFactory();
                    Loan request = LR.getLoan(loanType, "Requested loan", amount);
                    BankerConfirmation bc = new BankerConfirmation();
                    ManagerConfirmation mc = new ManagerConfirmation();
                    bc.setNextHandler(mc);

                    try {
                        bc.authorize(request);
                        Account a = getSelectedAccount();
                        if (a == null) {

                            return;
                        }
                        a.deposit(amount);
                        refreshListModel();
                        JOptionPane.showMessageDialog(frame, "The loan has been approved, and the money deposited");

                    } catch (Exception e1) {
                        JOptionPane.showMessageDialog(frame, e1.getMessage());
                    }
                }
            });
        }
    };

    private ActionListener btnDepositListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(list.isSelectionEmpty() == false) {
                double amount = Double.parseDouble( JOptionPane.showInputDialog(frame, "Please specify the amount you want to deposit") );
                try {
                    getSelectedAccount().deposit(amount);
                    JOptionPane.showMessageDialog(frame, "Deposit completed.");
                    refreshListModel();
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(frame, "Unable to deposit");
                }
            }
        }

    };

    private ActionListener btnWithdrawListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(list.isSelectionEmpty() == false) {
            try {
                double amount = Double.parseDouble(JOptionPane.showInputDialog(frame, "Please specify the amount you want to withdraw"));
                try {
                    getSelectedAccount().withdraw(amount);
                    JOptionPane.showMessageDialog(frame, "Withdrawal completed.");
                    refreshListModel();
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(frame, "Unable to withdraw");
                }
            } catch (Exception e2) {
                JOptionPane.showMessageDialog(frame, "Error- no money was withdraw");
            }}
        }

    };

    private ListSelectionListener listSelectionListener = new ListSelectionListener() {

        @Override
        public void valueChanged(ListSelectionEvent arg0) {
            if (list.isSelectionEmpty() == false) {

                Account selectedAccount = getSelectedAccount();

                String accountInfo = "Account type\n" + selectedAccount.getType() + " Account\n\nCards:\n";

                if (selectedAccount.getCards().size() == 0)
                    accountInfo += "None\n";
                else {
                    for (Card card : selectedAccount.getCards()) {
                        accountInfo += card.getType() + "\n";
                    }
                }

                accountInfo += "\nBalance\n" + selectedAccount.getBalance();

                txtAccountInfo.setText(accountInfo);
            }
        }

    };

    private ActionListener btnAddCardListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(list.isSelectionEmpty() == false) {
            JFrame f;

            JLabel l1;
            l1 = new JLabel();
            l1.setBounds(20, 25, 350, 30);
            l1.setText("choose card type");

            f = new JFrame("Loan");
            final JLabel label = new JLabel();
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setSize(400, 100);
            JButton b = new JButton("OK");
            b.setBounds(200, 100, 75, 20);
            String languages[] = {"Master", "Visa", "Leumi"};
            final JComboBox cb = new JComboBox(languages);
            cb.setBounds(50, 100, 90, 20);
            f.add(l1);
            f.add(cb);
            f.add(label);
            f.add(b);
            f.setLayout(null);
            f.setSize(350, 350);
            f.setVisible(true);

            b.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String cardType = ""
                            + cb.getItemAt(cb.getSelectedIndex());
                    if (!user.getAccounts().isEmpty()) {
                        CardFactory CR = new CardFactory();
                        Account a = getSelectedAccount();
                        if (a == null) {
                            return;
                        }
                        Card request = CR.createCard(cardType, a.getAccountOwnerName());
                        if (request != null){
                           System.out.println("a card was added");
                           a.addCard(request);
                            refreshListModel();
                        }
                    }
                }
            });


        }}
    };
    private ActionListener btnAddAccountListener = new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
            NewAccount window = new NewAccount(user);
            frame.setVisible(false);
            frame.dispose();
        }
    };


    private ActionListener btnSaveAccountListener = new ActionListener() {
        public void actionPerformed(ActionEvent arg0) {
            // write to file
            for(Account account : user.getAccounts()){
					try {
						String path = "C:\\Users\\hadar\\IdeaProjects\\BankApplication-DesignPatternsAssignment\\src\\Database\\"+user.getUsername()+account.getAccountNumber()+".txt";
						ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
						out.writeObject(user);
					} catch (IOException ioException) {
						ioException.printStackTrace();
					}}
        }
    };


    private Account getSelectedAccount() {
        if(list.isSelectionEmpty() == false) {
            for(Account account : user.getAccounts()) {
                if(account.getAccountNumber() == Integer.parseInt( (String) list.getSelectedValue() )) {
                    return account;
                }
            }
        }

        return null;
    };

    private void refreshListModel() {
        listModel.clear();
        txtAccountInfo.setText("");
        for(Account account : user.getAccounts()) {

            listModel.addElement(Integer.toString(account.getAccountNumber()));
        }
    }
};
