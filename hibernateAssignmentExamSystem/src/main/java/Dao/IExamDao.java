package Dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import Model.Questions;
import Model.User;

public class IExamDao {

	static SessionFactory factory;
	static Session ses;

	public IExamDao() {
		factory = new Configuration().configure("Hibernate.ExamSystem.xml").addAnnotatedClass(User.class)
				.buildSessionFactory();
	}

	public void createQuestions(Questions q) {
		ses = factory.openSession();
		Transaction tax = ses.beginTransaction();
		ses.save(q);
		tax.commit();
		System.out.println("Questions added Successfully!");
		ses.close();
	}

	public void removeQuestions(int id) {
		ses = factory.openSession();
		Transaction tax = ses.beginTransaction();
		Query<Questions> q = ses.createQuery("from Questions where questionid=:qid");
		q.setParameter("qid", id).uniqueResult();
		List<Questions> list = q.list();
		if (list.isEmpty()) {
			System.out.println("Incorrect Question ID!!");
		} else {
			Questions u = list.get(0);
			ses.delete(u);
			tax.commit();
			System.out.println("Question removed Successfully!!");
		}
		ses.close();
	}

	public Questions getQuestionById(int id) {
		ses = factory.openSession();
		Transaction tax = ses.beginTransaction();
		Query<Questions> q = ses.createQuery("from Questions where questionid=:qid");
		q.setParameter("qid", id).uniqueResult();
		List<Questions> list = q.list();
		Questions u =null;
		if (list.isEmpty()) {
			System.out.println("Incorrect Question ID!!");
		} else {
			u = list.get(0);
		}
		ses.close();
		return u;
	}

	public void modifyQuestions(Questions ques) {
		ses = factory.openSession();
		Transaction tax = ses.beginTransaction();
		Query<Questions> q = ses.createQuery("from Questions where questionid=:qid");
		q.setParameter("qid", ques.getQuestionid()).uniqueResult();
		List<Questions> list = q.list();
		if (list.isEmpty()) {
			System.out.println("Incorrect Question ID!!");
		} else {
			Questions u = list.get(0);
			u.setQuestionText(ques.getQuestionText());
			u.setOptionA(ques.getOptionA());
			u.setOptionB(ques.getOptionB());
			u.setOptionC(ques.getOptionC());
			u.setOptionD(ques.getOptionD());
			u.setCorrectAnswer(ques.getCorrectAnswer());
			ses.saveOrUpdate(u);
			tax.commit();
			System.out.println("Question changed Successfully!!");
		}
		ses.close();
	}
	public List<Questions> showAllQuestions() {
		ses = factory.openSession();
		Transaction tax = ses.beginTransaction();
		Query<Questions> q = ses.createQuery("from Questions");
		List<Questions> list = q.list();
		ses.close();
		return list;
	}
}
