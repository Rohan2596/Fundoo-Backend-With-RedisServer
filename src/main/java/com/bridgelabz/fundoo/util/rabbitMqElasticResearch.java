 package com.bridgelabz.fundoo.util;

import java.io.IOException;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.annotation.RabbitListeners;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bridgelabz.fundoo.elasticSearch.ElasticSearch;
import com.bridgelabz.fundoo.elasticSearch.IElasticSearch;
import com.bridgelabz.fundoo.elasticSearch.NoteContainer;
import com.bridgelabz.fundoo.notes.model.Notes;
import com.bridgelabz.fundoo.notes.respository.NotesRespository;


@Component
public class rabbitMqElasticResearch {

	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Value("${elastic.rabbitmq.exchange}")
	private String exchange1;

	@Value("${elastic.rabbitmq.routingkey}")
	private String routingkey1;
	
	@Autowired
	NotesRespository noteRespository;
	
	
	
	@Autowired
	NoteContainer notecontainer;
	  @Autowired
	  ElasticSearch elastic;
 
	
		public void rabitsendelastic(NoteContainer noteContainer) {
		System.out.println(noteContainer.getNotes());
			rabbitTemplate.convertAndSend(exchange1, routingkey1, noteContainer);
			System.out.println("send the messgae ");
			
		}
		
//		@RabbitListener(queues = "fundooelastic.queue1")
//		public void send(Notes notes) throws IOException {
//			System.out.println("elastic");
// 		System.out.println(notes);
//              			
//			if (!noteRespository.findById(notes.getId()).isPresent()) {
//			elastic.create(notes);
//			System.out.println("elastic serach ");
//		}else if(noteRespository.findById(notes.getId()).isPresent() && notes.isTrash()==true) {
//			elastic.deleteNote(notes.getId());
//			System.out.println("elastic serach ");}
//		else {
//				elastic.updateNote(notes);
//			System.out.println("elastic serach ");
//		}

//		}
		
		@RabbitListener(queues="fundooelastic.queue1")
		public void operation(NoteContainer noteContainer) throws IOException {
			Notes notes=noteContainer.getNotes();
			switch(noteContainer.getNoteoperation()) {
			case CREATE:
				System.out.println("Notes are created");
				elastic.create(notes);
				break;
			case UPDATE:
				System.out.println("Notes are updated");
				elastic.updateNote(notes);
				break;
			case DELETE:
				System.out.println("Notes are deleted");
				elastic.deleteNote(notes.getId());
				break;
			
			
			}
		}
		
		
		
}
