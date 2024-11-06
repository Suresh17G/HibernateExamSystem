package Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy =GenerationType.IDENTITY)
	int userid;
	
	@Column
	String name;
	String email;
	String password;
	String role;
	@OneToMany(cascade = CascadeType.ALL,targetEntity = Questions.class)
	List<Questions> questions;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public User(String name, String email, String password, String role) {
		
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
	}

	public User(String name, String email, String password, String role, List<Questions> questions) {
		
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.questions = questions;
	}
	public int getUserid() {
		return userid;
	}
	public void setUserid(int userid) {
		this.userid = userid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public List<Questions> getQuestions() {
		return questions;
	}
	public void setQuestions(List<Questions> questions) {
		this.questions = questions;
	}
	@Override
	public String toString() {
		return "User [userid=" + userid + ", name=" + name + ", email=" + email + ", password=" + password + ", role="
				+ role + "]";
	}
		
	
}
