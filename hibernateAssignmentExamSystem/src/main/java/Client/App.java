package Client;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import Model.User;
import Service.ExamService;
import Service.UserService;

public class App {
	
	public static void main(String[] args) {
		SessionFactory factory = new Configuration().configure("Hibernate.ExamSystem.xml").addAnnotatedClass(User.class)
				.buildSessionFactory();
		Session ses = factory.openSession();
		Transaction tax = ses.beginTransaction();
		if (ses.find(User.class, 1)==null) {
			User u = new User("Ajay", "ajay987@gmail.com", "admin@123", "admin");
			ses.save(u);
			tax.commit();
		}
		ses.close();
		UserService user = new UserService();
		Scanner sc = new Scanner(System.in);
		System.out.println("Exam System!\n");
		boolean flag = true;
		while (flag) {

			System.out.println("Menu!!");
			System.out.println("1) Register ");
			System.out.println("2)Login as user");
			System.out.println("3)Login as admin");
			System.out.println("4)Exit");
			System.out.print("Enter your choice:");
			int choice = sc.nextInt();
			switch (choice) {
			case 1:
				user.addUser();
				break;
			case 2:
				if (user.userLogin()) {
					System.out.println("User Login Successful!!");
					boolean userflag = true;
					while (userflag) {

						System.out.println("User Menu!!");
						System.out.println("1) Edit profile ");
						System.out.println("2)Take exam");
						System.out.println("3)Log out");
						System.out.print("Enter your choice:");
						int userchoice = sc.nextInt();
						switch (userchoice) {
						case 1:
							user.editProfile();
							break;
						case 2:
							user.takeExam();
							break;
						case 3:
							userflag = false;
							break;

						default:
							System.out.println("Please enter a Valid choice!!!");
							break;
						}
					}
				}else {
					System.out.println("Invalid credentials!!");
				}
				break;
			case 3:
				if (user.adminLogin()) {
					ExamService service=new ExamService();
					System.out.println("Admin Login Successful!!");
					boolean userflag = true;
					while (userflag) {

						System.out.println("Admin Menu!!");
						System.out.println("1) Add user");
						System.out.println("2) Remove user");
						System.out.println("3) Edit user");
						System.out.println("4) View all users");
						System.out.println("5) Add questions");
						System.out.println("6) Remove questions");
						System.out.println("7) Modify questions");
						System.out.println("8) View all questions");
						System.out.println("9) Logout");
						System.out.print("Enter your choice:");
						int userchoice = sc.nextInt();
						switch (userchoice) {
						case 1:
							user.addUser();
							break;
						case 2:
							user.removeUser();
							break;
						case 3:
							user.editProfile();
							break;
						case 4:
							user.viewUsers();
							break;
						case 5:
							service.addQuestion();
							break;
						case 6:
							service.deleteQuestion();
							break;
						case 7:
							service.editQuestion();
							break;
						case 8:
							service.viewQuestions();
							break;
						case 9:
							userflag = false;
							break;
						default:
							System.out.println("Please enter a Valid choice!!!");
							break;
						}
					}
				}else {
					System.out.println("Invalid credentials!!");
				}

				break;
			case 4:
				flag = false;
				break;

			default:
				System.out.println("Please enter a Valid choice!!!");
				break;
			}
		}
	}
}
