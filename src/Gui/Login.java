package Gui;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import Accounts.Account;
import Accounts.CreateAccount;

public class Login {

	private JFrame frmLogin;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frmLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmLogin = new JFrame();
		frmLogin.setTitle("Login");
		frmLogin.setBounds(100, 100, 450, 300);
		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmLogin.getContentPane().setLayout(null);
		frmLogin.getContentPane().setBackground(new Color(155, 210, 236));

		textField = new JTextField();
		textField.setBounds(170, 103, 86, 20);
		frmLogin.getContentPane().add(textField);
		textField.setColumns(10);
		
		Label label = new Label("Username");
		label.setBounds(102, 101, 62, 22);
		frmLogin.getContentPane().add(label);
		
		JButton btnNewButton = new JButton("Log In");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textField.getText();
				User user = Environment.isNameExist(name);
				if (user == null) {
					user = new User();
					user.setUsername(textField.getText());
					Environment.addUser(user);

					// write to file
					/*try {
						String path = "C:\\Users\\hadar\\IdeaProjects\\BankApplication-DesignPatternsAssignment\\src\\Database\\"+name+".txt";
						ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
						out.writeObject(user);
					} catch (IOException ioException) {
						ioException.printStackTrace();
					}*/

				}
//				else {
//					String path = "C:\\Users\\hadar\\IdeaProjects\\BankApplication-DesignPatternsAssignment\\src\\Database\\"+name+".txt";
//					User user = Environment.readObj(path);
////					Account newAccount = CreateAccount.createAccount(user.getUsername(), (String) list.getSelectedValue());
////					newAccount.setBalance(1000.0);
////					// System.out.println("hadar");
////					user.addAccount(newAccount);
//
//				}
				Accounts window = new Accounts(user);

				textField.setText("");
			}
		});
		btnNewButton.setBounds(266, 102, 89, 23);
		btnNewButton.setBackground(new Color(121, 190, 226));
		frmLogin.getContentPane().add(btnNewButton);
	}
}
