package com.bridgelabz.fundoo.user.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "@id", scope = Emailid.class)
public class Emailid {
private String To;
private String From;
private String Subject;
private String Body;

public Emailid() {
	super();
	
}
public String getTo() {
	return To;
}
public void setTo(String to) {
	To = to;
}
public String getFrom() {
	return From;
}
public void setFrom(String from) {
	From = from;
}
public String getSubject() {
	return Subject;
}
public void setSubject(String subject) {
	Subject = subject;
}
public String getBody() {
	return Body;
}
public void setBody(String body) {
	Body = body;
}
}
