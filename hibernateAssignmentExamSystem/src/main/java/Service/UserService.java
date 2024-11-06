package Service;

import java.util.List;
import java.util.Scanner;

import Dao.IUserDao;
import Model.Questions;
import Model.User;

public class UserService {

	Scanner sc= new Scanner(System.in);
	String name;
	String email;
	String password;
	IUserDao user=new IUserDao();

	public void addUser() {
		User u=new User();
		System.out.print("Enter name: ");
		u.setName(sc.nextLine());
		System.out.print("Enter email: ");
		u.setEmail(sc.nextLine());
		System.out.print("Enter password: ");
		u.setPassword(sc.nextLine());
		u.setRole("user");
		
		user.createUser(u);
	}
	public boolean userLogin() {

		System.out.print("Enter your email: ");
		email=sc.nextLine();
		System.out.print("Enter your password: ");
		password=sc.nextLine();
		return user.userLogin(email,password);
	}
	public boolean adminLogin() {

		System.out.print("Enter your email: ");
		email=sc.nextLine();
		System.out.print("Enter your password: ");
		password=sc.nextLine();
		return user.adminLogin(email,password);
	}
	
	public void editProfile() {
		System.out.print("Enter your email: ");
		email=sc.nextLine();
		System.out.print("Enter your new password: ");
		password=sc.nextLine();
		user.modifyuser(email,password);
	}
	public void removeUser() {
		System.out.print("Enter email ID: ");
		email=sc.nextLine();
		user.removeUser(email);
	}

	public void takeExam() {
		System.out.print("Enter email ID: ");
		email=sc.nextLine();
		int mark=user.takeExam(email);
		System.out.println("You scored : "+mark);
	}
	public void viewUsers() {
		List<User> list=user.showAllUsers();
		if (list.isEmpty()) {
			System.out.println("No Users Available!!");
		} else {
			for (User user : list) {
				System.out.println(user);	
			}
		}
	}
}
