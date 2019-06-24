package com.bridgelabz.fundoo.user.service;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import com.bridgelabz.fundoo.user.model.Emailid;
import com.bridgelabz.fundoo.util.TokenGenerators;

@Component
public class MailService {

	private JavaMailSender javaMailSender;

	@Autowired
	private AmqpTemplate rabbitTemplate;

	@Value("${fundoo.rabbitmq.exchange}")
	private String exchange;

	@Value("${fundoo.rabbitmq.routingkey}")
	private String routingkey;

	@Autowired
	public MailService(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	@Autowired
	private TokenGenerators tokenGenerators;
	
	@RabbitListener(queues = "${fundoo.rabbitmq.queue}")
	public void send(Emailid emailid) {
		System.out.println("Sending mail to receiver");
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(emailid.getFrom());
		message.setTo(emailid.getTo());
		message.setSubject(emailid.getSubject());
		message.setText(emailid.getBody());
	rabbitTemplate.convertAndSend(exchange, routingkey,emailid);
		javaMailSender.send(message);

		System.out.println("email sent successfully");
	}

	public String getlink(String link, long id) {
		return link + tokenGenerators.generateToken(id);
	}
//@RabbitListener(queues="${fundoo.rabbitmq.queue}")
//public void receive(Notes notes) {
//send(notes);
//	System.out.println(Emailid emailid);
//	
//	
//}
 
	public void rabitsend(Emailid emailid) {
		rabbitTemplate.convertAndSend(exchange, routingkey, emailid);
	}

}
