package net.scrappersite.ethryl.calendar.model;

import javax.persistence.Entity;

@Entity
public class User extends net.scrappersite.ethryl.calendar.model.TableEntity {

	private String Name;
	private String Login;
	private String Password;
	
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getLogin() {
		return Login;
	}
	public void setLogin(String login) {
		Login = login;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	
}
