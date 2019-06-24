package com.bridgelabz.fundoo.notes.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import com.bridgelabz.fundoo.labels.model.Labels;
import com.bridgelabz.fundoo.user.model.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Component
@Entity
@Table
public class Notes implements Serializable{
	private static final long serialVersionUID = 7156526077883281623L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="noteid")
	private long noteid;
	
 
	private long userId;
	
	private String title;

	private String description;

	private LocalDateTime modifiedDate;
	private LocalDateTime createdDate;
	private boolean isPin;
	private boolean isTrash;
	private boolean isArchieve;
	private  String color;
	private String reminder;
	
	public String getReminder() {
		return reminder;
	}

	public void setReminder(String reminder) {
		this.reminder = reminder;
	}

	@JsonIgnore
	@ManyToMany(cascade=CascadeType.ALL)
	private List<Labels> NLabels;
	
	@JsonIgnore
	@ManyToMany(cascade=CascadeType.ALL)
	private List<User> collabId;
	
	    public List<User> getCollabId() {
		return collabId;
	}

	public void setCollabId(List<User> collabId) {
		this.collabId = collabId;
	}

		public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

		


	public long getId() {
		return noteid;
	}

	public void setId(long id) {
		this.noteid = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserid(long userid) {
		this.userId = userid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public boolean isPin() {
		return isPin;
	}

	public void setPin(boolean isPin) {
		this.isPin = isPin;
	}

	public boolean isTrash() {
		return isTrash;
	}

	public void setTrash(boolean isTrash) {
		this.isTrash = isTrash;
	}

	public boolean isArchieve() {
		return isArchieve;
	}

	public void setArchieve(boolean isArchieve) {
		this.isArchieve = isArchieve;
	}

	public List<Labels> getNLabels() {
		return NLabels;
	}

	public void setNLabels(List<Labels> nLabels) {
		NLabels = nLabels;
	}

	
	public Notes() {
		
	}

	public Notes(long noteid, long userId, String title, String description, LocalDateTime modifiedDate,
			LocalDateTime createdDate, boolean isPin, boolean isTrash, boolean isArchieve, String color,
			String reminder, List<Labels> nLabels, List<User> collabId) {
		
		this.noteid = noteid;
		this.userId = userId;
		this.title = title;
		this.description = description;
		this.modifiedDate = modifiedDate;
		this.createdDate = createdDate;
		this.isPin = isPin;
		this.isTrash = isTrash;
		this.isArchieve = isArchieve;
		this.color = color;
		this.reminder = reminder;
		this.NLabels = nLabels;
		this.collabId = collabId;
	}

	@Override
	public String toString() {
		return "Notes [noteid=" + noteid + ", userId=" + userId + ", title=" + title + ", description=" + description
				+ ", modifiedDate=" + modifiedDate + ", createdDate=" + createdDate + ", isPin=" + isPin + ", isTrash="
				+ isTrash + ", isArchieve=" + isArchieve+ ", color=" + color+ ", reminder="+reminder+   "]";
	}

	



}
