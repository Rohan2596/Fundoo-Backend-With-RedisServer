package com.bridgelabz.fundoo.notes.dto;

public class NotesDto {
private String title;
private String description;
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
public NotesDto() {
	super();
	
}
@Override
public String toString() {
	return "NotesDto [title=" + title + ", description=" + description + "]";
}
public NotesDto(String title, String description) {
	super();
	this.title = title;
	this.description = description;
}

}
