package com.arshan.it.ltd.messageing.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqProducer {

	private static final Logger log = LoggerFactory.getLogger(RabbitMqProducer.class);

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void sendTo(String routingkey, String message) {
		log.info("Sending message using RabbitMq> ...");
		this.rabbitTemplate.convertAndSend(routingkey, message);
	}

}
