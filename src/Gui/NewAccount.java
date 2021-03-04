package Gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;

import net.miginfocom.swing.MigLayout;
import Accounts.Account;
import Accounts.CreateAccount;

public class NewAccount {
	
	private User
		user;

	private JFrame frmAddAccount;

	/**
	 * Create the application.
	 */
	public NewAccount(User user) {
		this.user = user;
		initialize();
		frmAddAccount.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		DefaultListModel listModel = new DefaultListModel();
		listModel.addElement("Basic");
		listModel.addElement("Save");
		listModel.addElement("Recurring Deposit");
		
		frmAddAccount = new JFrame();
		frmAddAccount.setTitle("Add Account");
		frmAddAccount.setBounds(100, 100, 450, 300);
		frmAddAccount.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmAddAccount.getContentPane().setLayout(new MigLayout("", "[grow][]", "[][grow]"));
		
		JLabel lblAccountType = new JLabel("Please select an account type");
		frmAddAccount.getContentPane().add(lblAccountType, "cell 0 0");
		
		JList list = new JList(listModel);
		frmAddAccount.getContentPane().add(list, "flowy,cell 0 1,grow");
		
		JButton btnSelect = new JButton("Select");
		btnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(list.getSelectedValue() != null) {
					try {
						Account newAccount = CreateAccount.makeAccount(user.getUsername(), (String) list.getSelectedValue());
						newAccount.setBalance(1000.0);
						// System.out.println("hadar");
						user.addAccount(newAccount);
						// PopupBox box = new PopupBox("Added account #" + newAccount.getAccountNumber() + " to your account");
						Accounts frame = new Accounts(user);
						frmAddAccount.setVisible(false); 
						frmAddAccount.dispose();
					} catch (Exception e1) {
						PopupBox box = new PopupBox(e1.getMessage());
					}
				}
			}
		});
		frmAddAccount.getContentPane().add(btnSelect, "cell 1 1");
	}

}
