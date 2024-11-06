package Dao;

import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import Model.Questions;
import Model.User;

public class IUserDao {

	static SessionFactory factory;
	static Session ses;

	public IUserDao() {
		factory = new Configuration().configure("Hibernate.ExamSystem.xml").addAnnotatedClass(User.class)
				.buildSessionFactory();
	}

	public void createUser(User u) {
		ses = factory.openSession();
		Transaction tax = ses.beginTransaction();
		Query<User> q = ses.createQuery("from User where email=:mail");
		q.setParameter("mail", u.getEmail()).uniqueResult();
		List<User> list = q.list();
		if (list.isEmpty()) {
			ses.save(u);
			tax.commit();
			System.out.println("User Registered Successfully!");
		} else {
			System.out.println("User already exists!!");
		}
		ses.close();
	}

	public boolean userLogin(String user, String password) {
		ses = factory.openSession();
		Transaction tax = ses.beginTransaction();
		Query<User> q = ses.createQuery("from User where email=:mail and password=:pass");
		q.setParameter("mail", user);
		q.setParameter("pass", password).uniqueResult();
		List<User> list = q.list();
		ses.close();
		if (!list.isEmpty()) {
			if (list.get(0).getRole().equals("admin")) {
				System.out.println("Your are not an User!!");
				return false;
			}
			return true;
		}
		return false;
	}

	public boolean adminLogin(String user, String password) {
		ses = factory.openSession();
		Transaction tax = ses.beginTransaction();
		Query<User> q = ses.createQuery("from User where email=:mail and password=:pass");
		q.setParameter("mail", user);
		q.setParameter("pass", password).uniqueResult();
		List<User> list = q.list();
		ses.close();
		if (!list.isEmpty()) {
			if (list.get(0).getRole().equals("user")) {
				System.out.println("Your are not an Admin!!");
				return false;
			}
			return true;
		}
		return false;
	}

	public void modifyuser(String user, String pass) {
		ses = factory.openSession();
		Transaction tax = ses.beginTransaction();
		Query<User> q = ses.createQuery("from User where email=:mail");
		q.setParameter("mail", user).uniqueResult();
		List<User> list = q.list();
		if (list.isEmpty()) {
			System.out.println("Incorrect Email ID!!");
		} else {
			User u = list.get(0);
			u.setPassword(pass);
			ses.saveOrUpdate(u);
			tax.commit();
			System.out.println("Password changed Successfully!!");
		}
		ses.close();
	}

	public void removeUser(String user) {
		ses = factory.openSession();
		Transaction tax = ses.beginTransaction();
		Query<User> q = ses.createQuery("from User where email=:mail");
		q.setParameter("mail", user).uniqueResult();
		List<User> list = q.list();
		if (list.isEmpty()) {
			System.out.println("Incorrect Email ID!!");
		} else {
			User u = list.get(0);
			ses.delete(u);
			tax.commit();
			System.out.println("User removed Successfully!!");
		}
		ses.close();
	}

	public int takeExam(String email) {
		int mark = 0;
		String choice;
		
		Scanner sc = new Scanner(System.in);
		ses = factory.openSession();
		Transaction tax = ses.beginTransaction();
		Query<User> q = ses.createQuery("from User where email=:mail");
		q.setParameter("mail", email).uniqueResult();
		List<User> list = q.list();
		if (list.isEmpty()) {
			System.out.println("Incorrect Email ID!!");
		} else {
			User u = list.get(0);
			IExamDao exam =new IExamDao();
			List<Questions> qlist=exam.showAllQuestions();
			if (qlist.isEmpty()) {
				System.out.println("No Questions Available!!");
			} else {
				for (Questions questions : qlist) {
					System.out.println("Q" + questions.getQuestionid() + ": " + questions.getQuestionText());
					System.out.println("A) " + questions.getOptionA() + "\tB) " + questions.getOptionB());
					System.out.println("C) " + questions.getOptionC() + "\tD) " + questions.getOptionD());
					System.out.print("Enter your option(A/B/C/D):");
					choice = sc.next();
					if (questions.getCorrectAnswer().equalsIgnoreCase(choice)) {
						mark++;
					}
				}
			}
		}
		ses.close();
		return mark;
	}
	public List<User> showAllUsers() {
		ses = factory.openSession();
		Transaction tax = ses.beginTransaction();
		Query<User> q = ses.createQuery("from User");
		List<User> list = q.list();
		ses.close();
		return list;
	}
}
