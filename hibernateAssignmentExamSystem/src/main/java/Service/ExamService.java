package Service;

import java.util.List;
import java.util.Scanner;

import Dao.IExamDao;
import Model.Questions;

public class ExamService {

	Scanner sc= new Scanner(System.in);
	
	IExamDao exam=new IExamDao();
	
	public void addQuestion() {
		Questions q=new Questions();
		System.out.print("Enter question text: ");
		q.setQuestionText(sc.nextLine());
		System.out.print("Enter Option A: ");
		q.setOptionA(sc.nextLine());
		System.out.print("Enter Option B: ");
		q.setOptionB(sc.nextLine());
		System.out.print("Enter Option C: ");
		q.setOptionC(sc.nextLine());
		System.out.print("Enter Option D: ");
		q.setOptionD(sc.nextLine());
		System.out.print("Enter correct option: ");
		q.setCorrectAnswer(sc.nextLine());
		exam.createQuestions(q);
	}
	public void deleteQuestion() {
		System.out.print("Enter question id to delete: ");
		int id=sc.nextInt();
		exam.removeQuestions(id);
	}
	public void editQuestion() {
		
		System.out.print("Enter question id to edit: ");
		int id=sc.nextInt();
		Questions q1=exam.getQuestionById(id);
		System.out.println(q1);
		Questions q2=new Questions();
		q2.setQuestionid(id);
		sc.nextLine();
		System.out.print("Enter new question text: ");
		q2.setQuestionText(sc.nextLine());
		System.out.print("Enter new Option A: ");
		q2.setOptionA(sc.nextLine());
		System.out.print("Enter new Option B: ");
		q2.setOptionB(sc.nextLine());
		System.out.print("Enter new Option C: ");
		q2.setOptionC(sc.nextLine());
		System.out.print("Enter new Option D: ");
		q2.setOptionD(sc.nextLine());
		System.out.print("Enter new correct option: ");
		q2.setCorrectAnswer(sc.nextLine());
		exam.modifyQuestions(q2);
	}
	public void viewQuestions() {
		List<Questions> list=exam.showAllQuestions();
		if (list.isEmpty()) {
			System.out.println("No Questions Available!!");
		} else {
			for (Questions questions : list) {
				System.out.println(questions);	
			}
		}
	}
}
