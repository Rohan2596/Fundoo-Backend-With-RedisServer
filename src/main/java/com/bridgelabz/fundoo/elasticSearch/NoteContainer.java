package com.bridgelabz.fundoo.elasticSearch;

import org.springframework.stereotype.Component;

import com.bridgelabz.fundoo.notes.model.Notes;
@Component
public class NoteContainer {
private Notes notes;
private NoteOperation noteoperation;
public Notes getNotes() {
	return notes;
}
public void setNotes(Notes notes) {
	this.notes = notes;
}
public NoteOperation getNoteoperation() {
	return noteoperation;
}
public void setNoteoperation(NoteOperation noteoperation) {
	this.noteoperation = noteoperation;
}


}
