package com.bridgelabz.fundoo.notes.controller;



import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.elasticSearch.ElasticSearch;
import com.bridgelabz.fundoo.exception.UserException;
import com.bridgelabz.fundoo.labels.model.Labels;
import com.bridgelabz.fundoo.notes.dto.NotesDto;
import com.bridgelabz.fundoo.notes.model.Notes;
import com.bridgelabz.fundoo.notes.service.NotesService;
import com.bridgelabz.fundoo.response.Response;
import com.bridgelabz.fundoo.user.model.User;


@RestController
@CrossOrigin(allowedHeaders="*",origins="*")
@RequestMapping("/users")
public class NotesController {
@Autowired
NotesService  notesService;
@Autowired
ElasticSearch elasticSearch;

@PostMapping("/createNotes")
	public ResponseEntity<Response> createNote(@RequestBody NotesDto notesDto,@RequestHeader String token) throws UserException, UnsupportedEncodingException{
		Response response=notesService.create(notesDto, token);
		System.out.println("response");
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
@GetMapping("/getnotes")
public List<Notes> readsingleNote(@RequestHeader String token) throws UserException, UnsupportedEncodingException{
	List<Notes> listnotes=notesService.read(token);
	
	return listnotes;
}
@GetMapping("/getcollabNotes")
public List<Notes> collabNote(@RequestHeader String token) throws UserException, UnsupportedEncodingException{
	List<Notes> listnotes=notesService.getCollabNotes(token);
	
	return listnotes;
}
@GetMapping("/getReminder")
public List<Notes> getReminder(@RequestHeader String token) throws IllegalArgumentException, UnsupportedEncodingException{
	List<Notes> listnotes=notesService.getReminder(token);
	return listnotes;
}

@PutMapping("/updatenotes")
public ResponseEntity<Response> updateNote(@RequestBody NotesDto notesDto,@RequestHeader String token,@RequestParam long id) throws UserException, UnsupportedEncodingException{
	System.out.println("Inside in updateNote");
	Response response=notesService.update(notesDto,token,id);
	System.out.println(response);
	return new ResponseEntity<>(response,HttpStatus.OK);
}
@DeleteMapping("/deletenotes")
public ResponseEntity<Response> deleteNote(@RequestHeader String token, @RequestParam int id) throws UserException, UnsupportedEncodingException{
	System.out.println("Inside delete note");
	Response response=notesService.delete(token,id);
	System.out.println(response);
	return new ResponseEntity<>(response,HttpStatus.OK);
}
@PutMapping("notes/trash")
public ResponseEntity<Response>trash(@RequestHeader String token,@RequestParam int id) throws UserException, UnsupportedEncodingException{
	System.out.println("Inside trash note");
	Response response=notesService.trash(token,id);
	System.out.println(response);
	return new ResponseEntity<>(response,HttpStatus.OK);
}
@PutMapping("notes/pin")
public ResponseEntity<Response>pin(@RequestHeader String token, @RequestParam int id) throws UserException, UnsupportedEncodingException{
	System.out.println("Inside pin ");
	Response response=notesService.pin(token,id);
	return new ResponseEntity<>(response,HttpStatus.OK);
}

@PutMapping("notes/archieve")
public ResponseEntity<Response>archieve(@RequestHeader String token,@RequestParam int id) throws UserException, UnsupportedEncodingException{
	System.out.println("Inside pin ");
	Response response=notesService.archieve(token,id);
	return new ResponseEntity<>(response,HttpStatus.OK);
}
@PutMapping("notes/setcolor")
public ResponseEntity<Response>color(@RequestHeader String token,@RequestParam long noteid,@RequestParam String color) throws IllegalArgumentException, UnsupportedEncodingException{
	System.out.println("setting the color of notes ");
	Response response=notesService.color(token, noteid, color);
	return new ResponseEntity<>(response,HttpStatus.OK);
	
}
@PutMapping("notes/addNotetolabel")
public ResponseEntity<Response> addNotetolabel(@RequestParam long labelid,@RequestHeader String token,@RequestParam long noteid) throws UserException, UnsupportedEncodingException{
	System.out.println("inside addnotelabel");
	Response response=notesService.addNotetolabel(labelid, token, noteid);
	return new ResponseEntity<>(response,HttpStatus.OK);
}
@PutMapping("notes/removeNotetolabel")
public ResponseEntity<Response> removeNotetolabel(@RequestParam long labelid,@RequestHeader String token,@RequestParam long noteid) throws UserException, UnsupportedEncodingException{
	System.out.println("inside addnotelabel");
	Response response=notesService.removeNotetolabel(labelid, token, noteid);
	return new ResponseEntity<>(response,HttpStatus.OK);
}

@GetMapping("/gettrashnotes")
public List<Notes> trashNote(@RequestHeader String token) throws UserException, UnsupportedEncodingException{
	List<Notes> listnotes=notesService.trashnotes(token);
	
	return listnotes;
}
@GetMapping("/getarchivenotes")
public List<Notes> archiveNote(@RequestHeader String token) throws UserException, UnsupportedEncodingException{
	List<Notes> archievenotes=notesService.archivenotes(token);
	
	return archievenotes;
}
@GetMapping("/getpinnotes")
public List<Notes> pinNote(@RequestHeader String token) throws UserException, UnsupportedEncodingException{
	List<Notes> pinnotes=notesService.pinnotes(token);
	
	return pinnotes;
}
@GetMapping("/getallNotelabel")
public List<Labels> allNotelabel(@RequestHeader String token,@RequestParam long noteid) throws UserException, UnsupportedEncodingException{
	List<Labels> allnoteslabel=notesService.getAlllabels(token, noteid);
	
	return allnoteslabel;
}
@GetMapping("/getallcollablist")
public List<User> allcollab(@RequestHeader String token,@RequestParam long noteid) throws UserException, UnsupportedEncodingException{
	List<User> allnoteslabel=notesService.getcollablist(token, noteid);
	
	return allnoteslabel;}


@PutMapping("notes/addcollabrator")
public ResponseEntity<Response> addcollaboratortonote(@RequestParam long noteid,@RequestHeader String token,@RequestParam String collabemailid) throws UserException, UnsupportedEncodingException{
	System.out.println("inside Add collaborator to note");
	Response response=notesService.addCollabtoNote(noteid, token, collabemailid);
	return new ResponseEntity<>(response,HttpStatus.OK);

}
@PutMapping("notes/removecollabrator")
public ResponseEntity<Response> removecollaboratortonote(@RequestParam long noteid,@RequestHeader String token,@RequestParam String collabemailid) throws IllegalArgumentException, UnsupportedEncodingException{
	System.out.println("inside remove collaborator to note");
	Response response=notesService.removeCollabtoNote(noteid, token, collabemailid);
	return new ResponseEntity<>(response,HttpStatus.OK);
}
@PutMapping("notes/reminder")
public ResponseEntity<Response>reminder(@RequestHeader String token,@RequestParam long noteid,@RequestParam String date) throws IllegalArgumentException, UnsupportedEncodingException{
	System.out.println("setting the color of notes ");
	Response response=notesService.reminder(token, noteid, date);
	return new ResponseEntity<>(response,HttpStatus.OK);
	
}
@PutMapping("notes/deletereminder")
public ResponseEntity<Response>reminder(@RequestHeader String token,@RequestParam long noteid) throws IllegalArgumentException, UnsupportedEncodingException{
	System.out.println("deleting reminder notes ");
	Response response=notesService.deletereminder(token, noteid);
	return new ResponseEntity<>(response,HttpStatus.OK);
	
}

@GetMapping("elasticSearch/getallNotes")
public List<Notes> findAll() throws Exception{
	return elasticSearch.searchData();
}

@GetMapping("elasticSearch/getnotesbytitle")
public List<Notes>findByTitle(@RequestParam String query,@RequestHeader String token) throws IllegalArgumentException, UnsupportedEncodingException{
	return  elasticSearch.searchall(query, token);
}

@GetMapping("redis/getnotes")
public Notes allbyredis(@RequestParam long id) throws IllegalArgumentException, UnsupportedEncodingException {
	return notesService.getallRedis(id);
}

 
}
