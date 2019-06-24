package com.bridgelabz.fundoo.user.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class UserDTO {

	@NotEmpty(message = "Enter Name")
	@Length(min = 3, max = 32, message = "length :3 to 32 characters")
	private String name;

	@NotEmpty(message = "Enter email address")
	@Pattern(regexp = "^\\w+[\\w-\\.]*\\@\\w+((-\\w+)|(\\w*))\\.[a-z]{2,3}$", message = "enetr valid email address..!")
	private String emailId;

	@NotEmpty(message = "Enter password ...!")
	@Length(min = 8, max = 32, message = "length :6 to 32 characters")
	private String password;

	@NotEmpty(message = "Enter mobile number")
//@Pattern(regexp = "[7-9] {1}[0-9]{9}", message = "Enter 10-digit mobile number") // ^[7-9][0-9]{9}$
	private String phNumber;

	public UserDTO(String name, String emailId, String password, String phNumber) {
		super();
		this.name = name;
		this.emailId = emailId;
		this.password = password;
		this.phNumber = phNumber;
	}

	@Override
	public String toString() {
		return "UserDTO [name=" + name + ", emailId=" + emailId + ", password=" + password + ", phNumber=" + phNumber
				+ "]";
	}

	public UserDTO() {
		super();

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhNumber() {
		return phNumber;
	}

	public void setPhNumber(String phNumber) {
		this.phNumber = phNumber;
	}

}
