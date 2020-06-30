package ua.guitarshop.objects;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Basic
	private String surname;
	@Basic
	private String email;
	@Basic
	private String login;
	@Basic
	private String password;
	
	public User() {
		
	}
	
	public User(String surname, String email, String login, String password) {
        this.surname = surname;
        this.email = email;
        this.login = login;
        this.password = password;
    }
	
	public void setId(int id) {
		this.id=id;
	}
	
	public int getId() {
		return this.id;
	}
	
	public void setSurname(String surname) {
		this.surname=surname;
	}
	
	public String getSurname() {
		return this.surname;
	}
	
	public void setEmail(String email) {
		this.email=email;
	}
	
	public String getEmail() {
		return this.email;
	}
	
	public void setLogin(String login) {
		this.login=login;
	}
	
	public String getLogin() {
		return this.login;
	}
	
	public void setPassword(String password) {
		this.password=password;
	}
	
	public String getPassword() {
		return this.password;
	}

}
