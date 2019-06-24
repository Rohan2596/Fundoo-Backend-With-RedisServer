package com.bridgelabz.fundoo.labels.controller;

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

import com.bridgelabz.fundoo.exception.UserException;
import com.bridgelabz.fundoo.labels.dto.LabelsDto;
import com.bridgelabz.fundoo.labels.model.Labels;
import com.bridgelabz.fundoo.labels.service.LabelService;
import com.bridgelabz.fundoo.notes.model.Notes;
import com.bridgelabz.fundoo.response.Response;

@RestController
@CrossOrigin(allowedHeaders="*",origins="*")
@RequestMapping("/users")
public class LabelController {
@Autowired
LabelService labelService;

@PostMapping("/labels/create")
public ResponseEntity<Response> createlabels(@RequestBody LabelsDto labelsDto,@RequestHeader String token) throws UserException, UnsupportedEncodingException{
	
	Response response=labelService.createlabel(labelsDto,token);
	System.out.println(response);
	return  new ResponseEntity<>(response,HttpStatus.OK) ;
}

@PutMapping("/labels/update")
public ResponseEntity<Response>updatelabels(@RequestBody LabelsDto labelsDto,@RequestHeader String token,@RequestParam long id) throws UserException, UnsupportedEncodingException{
	Response response;
		response = labelService.updatelabel(labelsDto,token,id);
		System.out.println(response);
		return new ResponseEntity<>(response,HttpStatus.OK);
	



}
@DeleteMapping("/labels/delete")
public ResponseEntity<Response> deletelabels(@RequestHeader String token,@RequestParam long id) throws UserException, UnsupportedEncodingException{
	Response response=labelService.deletelabel(token,id);
	System.out.println(response);
	return new ResponseEntity<>(response,HttpStatus.OK);
}
@GetMapping("/labels/readlabels")	
public ResponseEntity<Response> readlabels(LabelsDto labelsDto){
		Response response=labelService.readlabel(labelsDto);
		System.out.println(response);
		return new ResponseEntity<>(response,HttpStatus.OK);
	}

@PutMapping("/labels/addnote")
public ResponseEntity<Response> addLabeltoNote(@RequestParam long labelid,@RequestHeader String token,@RequestParam long noteid) throws IllegalArgumentException, UnsupportedEncodingException{
	Response response=labelService.addLabelNote(labelid, token, noteid);
	System.out.println(response);
	return new ResponseEntity<>(response,HttpStatus.OK);
}

@GetMapping("/labels/removenote")
public ResponseEntity<Response> removeLabeltoNote(long labelid, String token, long noteid){
	Response response=labelService.removeLabelNote(labelid, token, noteid) ;
	System.out.println(response);
	return new ResponseEntity<>(response,HttpStatus.OK);
}
@GetMapping("/getlabels")
public List<Labels> getlabels(@RequestHeader String token) throws IllegalArgumentException, UnsupportedEncodingException{
	List<Labels> listlabels=labelService.allLabels(token);
	return listlabels;
}
@GetMapping("/getlabelsOfNotes")
public List<Notes> getlabelsofNote(@RequestParam long labelid,@RequestHeader String token) throws IllegalArgumentException, UnsupportedEncodingException{
	List<Notes> listnotes=labelService.alllabelNotes(labelid, token);
	return listnotes;
}

}