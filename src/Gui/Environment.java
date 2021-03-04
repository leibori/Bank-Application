package Gui;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class Environment {
	
	private static ArrayList<User> users = new ArrayList<>();

	private Environment() {
		// TODO Auto-generated constructor stub
	}

	public static final ArrayList<User> getUsers() {
		return users;
	}

	public static User readObj(String path){
//		try (FileInputStream fis = new FileInputStream(path);
//			 ObjectInputStream ois = new ObjectInputStream(fis)) {
//
//			// read object from file
//			//User user = (User) ois.readObject();
//
//			// print object
//			System.out.println(user);
//			return user;
//		} catch (IOException | ClassNotFoundException ex) {
//			ex.printStackTrace();
//		}
		return null;
	}

	public static User isNameExist(String name) {

		String path = "C:\\Users\\hadar\\IdeaProjects\\BankApplication-DesignPatternsAssignment\\src\\Database\\"+name+".txt";
		File f = new File(path);
		if(f.exists() && !f.isDirectory()){
			// read file into obj
			User u = readObj(path);
			System.out.println("welcome back");
			if (u != null)
				System.out.println(u.getUsername() + "--" + u.getAccounts());
			return u;
		}
		return null;
	}

	public static final void setUsers(ArrayList<User> users) {
		Environment.users = users;
	}
	
	public static final void addUser(User user) {
		Environment.users.add(user);
	}
}
