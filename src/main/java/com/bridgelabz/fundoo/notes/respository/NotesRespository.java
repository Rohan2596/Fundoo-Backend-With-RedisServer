package com.bridgelabz.fundoo.notes.respository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundoo.notes.model.Notes;

@Repository
public interface  NotesRespository  extends JpaRepository<Notes,Long>{
	public Optional<Notes> findBytitle(String title);
	public Optional<Notes> deleteBytitle(String title);

	public Notes findByNoteidAndUserId(long noteid , long userId);
	public List<Notes> findByUserId(long userid);
	
	
	}
