package com.bridgelabz.fundoo.labels.service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import com.bridgelabz.fundoo.exception.UserException;
import com.bridgelabz.fundoo.labels.dto.LabelsDto;
import com.bridgelabz.fundoo.labels.model.Labels;
import com.bridgelabz.fundoo.labels.respository.LabelRespository;
import com.bridgelabz.fundoo.notes.model.Notes;
import com.bridgelabz.fundoo.notes.respository.NotesRespository;
import com.bridgelabz.fundoo.response.Response;
import com.bridgelabz.fundoo.user.model.User;
import com.bridgelabz.fundoo.user.respository.UserRespository;
import com.bridgelabz.fundoo.util.ResponseStatus;
import com.bridgelabz.fundoo.util.TokenGenerators;

@PropertySource("classpath:message.properties")
@Service
public class LabelService implements ILabelsService {
	@Autowired
	LabelRespository labelRespository;
	@Autowired
	ModelMapper modelMapper;
	@Autowired
	Environment environment;
	@Autowired
	TokenGenerators tokenGenerators;
	@Autowired
	UserRespository userRespository;
	@Autowired
	NotesRespository notesRespository;

	@Override
	public Response createlabel(LabelsDto labelsDto, String token)
			throws UserException, UnsupportedEncodingException {
		Response response = null;
		long token1 = tokenGenerators.decodeToken(token);
		Optional<Labels> labels1 = labelRespository.findByLabelName(labelsDto.getLabelName());
		if (labels1.isPresent()) {
			System.out.println("labels are present");
			response = ResponseStatus.statusinfo(environment.getProperty("status.failure.labels.created"),
					Integer.parseInt(environment.getProperty("status.failure.labels.code")));
		} else {
			Labels labels = modelMapper.map(labelsDto, Labels.class);
			Optional<User> user = userRespository.findById(token1);
			labels.setUserId(token1);
			labels.setCreatedDateTime(LocalDateTime.now());
			user.get().getLabels().add(labels);
			labelRespository.save(labels);
			userRespository.save(user.get());

			response = ResponseStatus.statusinfo(environment.getProperty("status.success.labels.created"),
					Integer.parseInt(environment.getProperty("status.success.labels.code")));
		}
		return response;
	}

	@Override
	public Response updatelabel(LabelsDto labelsDto, String token, long id)
			throws UserException, UnsupportedEncodingException {
		Response response = null;
//		if(labelsDto.getLabelName().isEmpty()) {
//			System.out.println("Notes is empty");
//		}
		long Userid = tokenGenerators.decodeToken(token);
		Labels labels = labelRespository.findByLabelIdAndUserId(id, Userid);

		System.out.println("labels are present");
		labels.setLabelName(labelsDto.getLabelName());
		labels.setModifiedDateTime(LocalDateTime.now());
		labelRespository.save(labels);
		response = ResponseStatus.statusinfo(environment.getProperty("status.success.labels.created"),
				Integer.parseInt(environment.getProperty("status.success.labels.code")));

		return response;
	}

	@Override
	public Response readlabel(LabelsDto labelsdDto) {
		Response response = null;
		Optional<Labels> labels1 = labelRespository.findByLabelName(labelsdDto.getLabelName());
		if (labels1.isPresent()) {
			System.out.println("labels is present");
			response = ResponseStatus.statusinfo(environment.getProperty("status.success.labels.created"),
					Integer.parseInt(environment.getProperty("status.success.labels.code")));

		} else {

			System.out.println("labels  are not present present");
			response = ResponseStatus.statusinfo(environment.getProperty("status.failure.labels.created"),
					Integer.parseInt(environment.getProperty("status.failure.labels.code")));
		}
		return response;
	}

	@Override
	public Response deletelabel(String token, long id)
			throws UserException, UnsupportedEncodingException {
		Response response = null;
	   long Userid = tokenGenerators.decodeToken(token);
		Labels labels = labelRespository.findByLabelIdAndUserId(id, Userid);
		labelRespository.delete(labels);
		response = ResponseStatus.statusinfo(environment.getProperty("status.success.labels.created"),
				Integer.parseInt(environment.getProperty("status.success.labels.code")));

		return response;
	}

	@Override
	public Response addLabelNote(long labelid, String token, long noteid) throws IllegalArgumentException, UnsupportedEncodingException {
		Response response=null;
		
		long id;
		
			id = tokenGenerators.decodeToken(token);
			Optional<User> user = userRespository.findById(id);
			Labels labels = labelRespository.findByLabelIdAndUserId(labelid, id);
			Notes notes = notesRespository.findByNoteidAndUserId(noteid, id);
		    Optional<Labels> labels1=labelRespository.findByLabelId(labelid);
			if (notes.getNLabels().contains(labels)==false && user.isPresent()) {
				labels.setModifiedDateTime(LocalDateTime.now());
				labels.getLNotes().add(notes);
				notes.getNLabels().add(labels);
				labels.setLabelId(labelid);
				labelRespository.save(labels);
				notesRespository.save(notes);
				response = ResponseStatus.statusinfo(environment.getProperty("status.success.labels.created"),
						Integer.parseInt(environment.getProperty("status.success.labels.code")));
			return response;
			}else {
		response = ResponseStatus.statusinfo(environment.getProperty("status.failure.labels.created"),
				Integer.parseInt(environment.getProperty("status.failure.labels.code")));
			
		return response;
			}
	}

	@Override
	public Response removeLabelNote(long labelid, String token, long noteid) {
		Response response=null;
		
		long id;
		try {
			id = tokenGenerators.decodeToken(token);
			Optional<User> user = userRespository.findById(id);
			Labels labels = labelRespository.findByLabelIdAndUserId(labelid, id);
			Notes notes = notesRespository.findByNoteidAndUserId(noteid, id);
			if (user.isPresent()) {
				labels.setModifiedDateTime(LocalDateTime.now());
				labels.getLNotes().add(notes);
				notes.getNLabels().remove(labels);
				labelRespository.save(labels);
				notesRespository.save(notes);
				response = ResponseStatus.statusinfo(environment.getProperty("status.success.labels.created"),
						Integer.parseInt(environment.getProperty("status.success.labels.code")));
			
			}
		} catch (IllegalArgumentException e) {

			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {

			e.printStackTrace();
		}
		response = ResponseStatus.statusinfo(environment.getProperty("status.failure.labels.created"),
				Integer.parseInt(environment.getProperty("status.failure.labels.code")));

		return response;

	}

	@Override
	public List<Labels> allLabels(String token) throws IllegalArgumentException, UnsupportedEncodingException {
		long userid=tokenGenerators.decodeToken(token);
		List<Labels> labels1=(List<Labels>)labelRespository.findByUserId(userid);
		List<Labels> listlabels=new ArrayList<>();
		for(Labels userlabels:labels1) {
			Labels labels=modelMapper.map(userlabels,Labels.class);
			System.out.println("labels diaplay");
			listlabels.add(labels);
			
		}

		return listlabels;
	}

	@Override
	public List<Notes> alllabelNotes(long labelid, String token) throws IllegalArgumentException, UnsupportedEncodingException {
		long userid = tokenGenerators.decodeToken(token);
		Optional<User> user = userRespository.findById(userid);
		if (user.isPresent()) {
			Labels label = labelRespository.findByLabelIdAndUserId(labelid, userid);
		
				List<Notes> listlabels = label.getLNotes();

				
				return listlabels;
		

		} else {
			throw new UserException("user is not present");
		}

	}




}


