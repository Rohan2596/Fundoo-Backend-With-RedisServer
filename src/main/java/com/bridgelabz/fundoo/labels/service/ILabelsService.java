
package com.bridgelabz.fundoo.labels.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.springframework.web.bind.annotation.RequestHeader;

import com.bridgelabz.fundoo.exception.UserException;
import com.bridgelabz.fundoo.labels.dto.LabelsDto;
import com.bridgelabz.fundoo.labels.model.Labels;
import com.bridgelabz.fundoo.notes.model.Notes;
import com.bridgelabz.fundoo.response.Response;

public interface ILabelsService {
Response createlabel(LabelsDto labelsDts,String token) throws UserException, UnsupportedEncodingException;
Response updatelabel(LabelsDto labelsDto,String token,long id) throws UserException, UnsupportedEncodingException;
Response readlabel(LabelsDto labelsdDto);
Response deletelabel(String token,long id) throws UserException, UnsupportedEncodingException;
Response addLabelNote(long labelid,String token,long noteid) throws IllegalArgumentException, UnsupportedEncodingException;
Response removeLabelNote(long labelid,String token,long noteid);
List<Labels> allLabels(String token) throws IllegalArgumentException, UnsupportedEncodingException;
//List<Labels> allLabelsInNote(long noteid, String token) throws IllegalArgumentException, UnsupportedEncodingException;

List<Notes>alllabelNotes(long labelid,String token) throws IllegalArgumentException, UnsupportedEncodingException;
}
