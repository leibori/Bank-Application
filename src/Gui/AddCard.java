package Gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import Accounts.Account;
import Cards.Card;
import Cards.CardFactory;
import net.miginfocom.swing.MigLayout;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddCard {

	private JFrame frame;

	private Account
		account;

	private User
		user;

	/**
	 * Create the application.
	 */
	public AddCard(User user, Account account) {
		this.user = user;
		this.account = account;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 290, 85);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[][grow][]", "[grow]"));

		JLabel lblCardType = new JLabel("Card type");
		frame.getContentPane().add(lblCardType, "cell 0 0");

		DefaultListModel listModel = new DefaultListModel();
		listModel.addElement("Debit");
		listModel.addElement("Credit");

		JList list = new JList(listModel);
		list.setVisibleRowCount(2);
		frame.getContentPane().add(list, "cell 1 0,grow");

		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(list.getSelectedValue() != null) {
					try {
						Card card = CardFactory.createCard( "Master", user.getUsername());
						account.addCard(card);
						Accounts window = new Accounts(user);
						frame.setVisible(false);
						frame.dispose();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
			}
		});
		frame.getContentPane().add(btnAdd, "cell 2 0");

		frame.setTitle("Add Card to account #" + account.getAccountNumber());

		frame.setVisible(true);
	}

}
