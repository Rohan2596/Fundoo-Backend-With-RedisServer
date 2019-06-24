package com.bridgelabz.fundoo.configuartion;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 
 * @author Rohan Kadam
 *Purpose:Rabbit mQ Configuration (Helps for queueing purpose )
 */
@Configuration
public class RabbitMqConfiguration {

	@Value("${fundoo.rabbitmq.queue}")
	String queueName;

	@Value("${fundoo.rabbitmq.exchange}")
	String exchange;

	
	
	
	@Value("${fundoo.rabbitmq.routingkey}")
	private String routingkey;
	
	@Value("${elastic.rabbitmq.queue}")
	String queueName1;


	@Value("${elastic.rabbitmq.routingkey}")
	private String routingkey1;
	
	@Value("${elastic.rabbitmq.exchange}")
	String exchange1;
	

	/**
	 * Purpose:Provides Bean for Queue(Mailing Queue)
	 * @return Bean for Queue (Mailing Queue)
	 */
	@Bean
	Queue queue() {
		return new Queue(queueName, false);
	}

	/**
	 * Purpose: Provides Bean for exchange(Mailing Exchange)
	 * @return Exchange Bean
	 */
	@Bean
	DirectExchange exchange() {
		return new DirectExchange(exchange);
	}
	
	/**
	 * Purpose: Provides Bean for exchange(Elastic Search)
	 * @return Exchange Bean 
	 */
	@Bean
	DirectExchange exchange1() {
		return new DirectExchange(exchange1);
	}

	
	/**
	 * @param queue:-Queue name
	 * @param exchange:-Direct Exchange
	 * @return
	 */
	@Bean
	Binding binding(Queue queue, DirectExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(routingkey);
	}

	/**
	 * Purpose:- For Converting Message(Jackson to json Message)
	 * @return 
	 */
	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	

	/**
	 * Purpose: Bean for Queue(Elastic Search)
	 * @return 
	 */
	@Bean
	Queue queue1() {
		return new Queue(queueName1, false );
	}
	
	/**
	 * @param queue1 = Queue Name(Elastic Search)
	 * @param exchange1= exchange key (Elastic Search)
	 * @return
	 */
	@Bean
	Binding binding1(Queue queue1, DirectExchange exchange1) {
		return BindingBuilder.bind(queue1).to(exchange1).with(routingkey1);
	}
}
