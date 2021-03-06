package Gui;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import Accounts.Account;
public class User implements Serializable {
	
	private String username;
	private ArrayList<Account> accounts = new ArrayList<>();

	public User() { }
	
	public final String getUsername() {
		return username;
	}

	public final void setUsername(String username) {
		this.username = username;
	}
	
	public final ArrayList<Account> getAccounts() {
		return accounts;
	}

	public final void setAccounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}
	
	public final void addAccount(Account account) {
		this.accounts.add(account);
		try {
			String path = "C:\\Users\\hadar\\IdeaProjects\\BankApplication-DesignPatternsAssignment\\src\\Database\\"+username+".txt";
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path));
			// out.writeObject(this);
		} catch (IOException ioException) {
			ioException.printStackTrace();
		}
	}
}
