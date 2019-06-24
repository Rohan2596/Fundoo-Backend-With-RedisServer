package com.bridgelabz.fundoo.notes.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.bridgelabz.fundoo.exception.UserException;
import com.bridgelabz.fundoo.labels.model.Labels;
import com.bridgelabz.fundoo.notes.dto.NotesDto;
import com.bridgelabz.fundoo.notes.model.Notes;
import com.bridgelabz.fundoo.response.Response;
import com.bridgelabz.fundoo.user.model.User;

public interface INotesService {
	Response create(NotesDto notesDto, String token) throws UserException, UnsupportedEncodingException;

	List<Notes> read(String token) throws UserException, UnsupportedEncodingException;

	Response update(NotesDto notesDto, String token, long id) throws UserException, UnsupportedEncodingException;

	Response delete(String token, int id) throws UserException, UnsupportedEncodingException;

	Response trash(String token, int id) throws UserException, UnsupportedEncodingException;

	Response pin(String token, int id) throws UserException, UnsupportedEncodingException;

	Response archieve(String token, int id) throws UserException, UnsupportedEncodingException;

	Response color(String token, long noteid, String color)
			throws IllegalArgumentException, UnsupportedEncodingException;

	Response reminder(String token,long noteid,String date) throws IllegalArgumentException, UnsupportedEncodingException;
	Response deletereminder(String token,long noteid) throws IllegalArgumentException, UnsupportedEncodingException;
	
	Response addNotetolabel(long labelid, String token, long noteid) throws UserException, UnsupportedEncodingException;

	Response removeNotetolabel(long labelid, String token, long noteid)
			throws UserException, UnsupportedEncodingException;
	Response addCollabtoNote(long noteid,String token,String collabemailid) throws IllegalArgumentException, UnsupportedEncodingException;
 Response removeCollabtoNote(long noteid,String token,String collabemailid) throws IllegalArgumentException, UnsupportedEncodingException;
	List<Notes> trashnotes(String token) throws UserException, UnsupportedEncodingException;

	List<Notes> archivenotes(String token) throws UserException, UnsupportedEncodingException;

	List<Notes> pinnotes(String token) throws UserException, UnsupportedEncodingException;

	List<Labels> getAlllabels(String token, long noteid) throws IllegalArgumentException, UnsupportedEncodingException;

	List<User> getcollablist(String token, long noteid) throws IllegalArgumentException, UnsupportedEncodingException;
List<Notes> getCollabNotes(String token) throws IllegalArgumentException, UnsupportedEncodingException;
	
	
//	List<Notes> getAllreminder(String token, long noteid) throws IllegalArgumentException, UnsupportedEncodingException;

List<Notes>  getReminder(String token) throws IllegalArgumentException, UnsupportedEncodingException;
//List<Notes> getReminderofNote(String token,long noteid) throws IllegalArgumentException, UnsupportedEncodingException;

//Notes getallRedis(String token) throws IllegalArgumentException, UnsupportedEncodingException;

Notes getallRedis(long id) throws IllegalArgumentException, UnsupportedEncodingException;

}
