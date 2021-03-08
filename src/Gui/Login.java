package Gui;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


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
		// frmLogin.getContentPane().setBackground(new Color(0, 0, 0, .5f));

		textField = new JTextField();
		textField.setBounds(170, 103, 86, 20);
		frmLogin.getContentPane().add(textField);
		textField.setColumns(10);

		JLabel label = new JLabel("Enter Your Name:");
		label.setBounds(68, 101, 200, 22);
		frmLogin.getContentPane().add(label);
		label.setOpaque(false);

		JLabel headline = new JLabel("Welcome to the first Java Bank");
		headline.setBounds(40, 2, 400, 100);
		frmLogin.getContentPane().add(headline );
		headline.setFont(new Font("Courier", Font.BOLD,24));
		headline.setOpaque(false);


		ImageIcon icon = new ImageIcon(this.getClass().getResource("pink-sky-cover.jpg"));
		JLabel back = new JLabel("",icon, JLabel.CENTER);
		back.setBounds(0,0,500,500);
		frmLogin.add(back);

		JButton btnNewButton = new JButton("Log In");

		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = textField.getText();
				User user = Environment.isNameExist(name);
				if (user == null) {
					user = new User();
					user.setUsername(textField.getText());
					Environment.addUser(user);
				}

				Accounts window = new Accounts(user);

				textField.setText("");
			}

		});

		btnNewButton.setBounds(266, 102, 89, 23);
		btnNewButton.setBackground(new Color(220, 157, 175));
		frmLogin.getContentPane().add(btnNewButton);
		btnNewButton.setVisible(true);
	}
}
