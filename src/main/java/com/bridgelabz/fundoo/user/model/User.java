
package com.bridgelabz.fundoo.user.model;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


import org.springframework.stereotype.Component;

import com.bridgelabz.fundoo.labels.model.Labels;
import com.bridgelabz.fundoo.notes.model.Notes;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long userId;

	@NotEmpty(message = "Please provide valid Name")
	@NotNull(message = "Please provide Valid Name")
	private String name;
   
	
	@NotEmpty(message = "Please provide valid Name")
	@NotNull(message = "Please provide Valid Name")
	@Email(message = "Please provide Valid Emailid")
	private String emailId;

	@NotNull(message = "Please provide Valid Phone number")
	@NotEmpty(message = "Please provide Valid Phone Number")
	private String phNumber;

	@NotNull(message = "Please provide Valid Password")
	@NotEmpty(message = "Please provide Valid Password")
	private String password;

	private boolean isVerified = false;
	private LocalDateTime registeredDate = LocalDateTime.now();
	private LocalDateTime modifiedDate = LocalDateTime.now();
	private String profilePic;
	
	
	
   public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

@OneToMany(cascade=CascadeType.ALL)
	private List<Notes> notes;
 @OneToMany(cascade=CascadeType.ALL)
   private List<Labels> labels;
 
@JsonIgnore 
@ManyToMany(cascade=CascadeType.ALL) 
 private List<Notes> collabnotes;
   
	public List<Notes> getCollabnotes() {
	return collabnotes;
}

public void setCollabnotes(List<Notes> collabnotes) {
	this.collabnotes = collabnotes;
}

	public List<Labels> getLabels() {
	return labels;
}

public void setLabels(List<Labels> labels) {
	this.labels = labels;
}

	public List<Notes> getNotes() {
		return notes;
	}

	public void setNotes(List<Notes> notes) {
		this.notes = notes;
	}

	public long getId() {
		return userId;
	}

	public void setId(long id) {
		this.userId = id;
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

	public String getPhNumber() {
		return phNumber;
	}

	public void setPhNumber(String phNumber) {
		this.phNumber = phNumber;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean isVerified) {
		this.isVerified = isVerified;
	}

	public LocalDateTime getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(LocalDateTime registeredDate) {
		this.registeredDate = registeredDate;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public User() {
		super();

	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", name=" + name + ", emailId=" + emailId + ", phNumber=" + phNumber
				+ ", password=" + password + ", isVerified=" + isVerified + ", registeredDate=" + registeredDate
				+ ", modifiedDate=" + modifiedDate + ", notes=" + notes + ", labels=" + labels +
				 ", collabnotes=" + collabnotes +  "]";
	}


	

}
