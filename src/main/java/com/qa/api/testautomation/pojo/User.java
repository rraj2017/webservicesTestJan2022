package com.qa.api.testautomation.pojo;
//POJO Template for user
/**
 * This class is a POJO class which is implemented using the encapsulation concept of java
 * @author rituraj
 *
 */
public class User {
	//class variables :	
	private String name;
	private String gender;
	private String dob;
	private String email;
	private String phone;
	private String status;
	
	//constructor:
	public User(String name, String gender, String dob, String email, String phone, String status) {
		this.name = name;
		this.gender = gender;
		this.dob = dob;
		this.email = email;
		this.phone = phone;
		this.status = status;
	}
     //getter and setter methods:
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
