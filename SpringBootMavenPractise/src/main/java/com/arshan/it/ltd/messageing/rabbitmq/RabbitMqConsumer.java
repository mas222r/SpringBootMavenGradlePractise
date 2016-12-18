package com.arshan.it.ltd.messageing.rabbitmq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMqConsumer {

	private static final Logger log = LoggerFactory.getLogger(RabbitMqConsumer.class);

	@RabbitListener(queues = "${myRabbitQueue}")
	public void handler(String message) {
		log.info("Consumeing message by Consumer using Rabbit MQ> " + message);
	}

}
