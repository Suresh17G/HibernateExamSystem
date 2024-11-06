package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Questions {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int questionid;
	@Column
	String questionText;
	String optionA;
	String optionB;
	String optionC;
	String optionD;
	String CorrectAnswer;

	public Questions() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Questions(int questionid, String questionText, String optionA, String optionB, String optionC,
			String optionD, String correctAnswer) {
		super();
		this.questionid = questionid;
		this.questionText = questionText;
		this.optionA = optionA;
		this.optionB = optionB;
		this.optionC = optionC;
		this.optionD = optionD;
		CorrectAnswer = correctAnswer;
	}

	public int getQuestionid() {
		return questionid;
	}

	public void setQuestionid(int questionid) {
		this.questionid = questionid;
	}

	public String getQuestionText() {
		return questionText;
	}

	public void setQuestionText(String questionText) {
		this.questionText = questionText;
	}

	public String getOptionA() {
		return optionA;
	}

	public void setOptionA(String optionA) {
		this.optionA = optionA;
	}

	public String getOptionB() {
		return optionB;
	}

	public void setOptionB(String optionB) {
		this.optionB = optionB;
	}

	public String getOptionC() {
		return optionC;
	}

	public void setOptionC(String optionC) {
		this.optionC = optionC;
	}

	public String getOptionD() {
		return optionD;
	}

	public void setOptionD(String optionD) {
		this.optionD = optionD;
	}

	public String getCorrectAnswer() {
		return CorrectAnswer;
	}

	public void setCorrectAnswer(String correctAnswer) {
		CorrectAnswer = correctAnswer;
	}

	
	@Override
	public String toString() {
		return "Question " + questionid + ": " + questionText + "\noptionA=" + optionA
				+ "\toptionB=" + optionB + "\noptionC=" + optionC + "\toptionD=" + optionD + "\nCorrectAnswer="
				+ CorrectAnswer + "\n";
	}
}
