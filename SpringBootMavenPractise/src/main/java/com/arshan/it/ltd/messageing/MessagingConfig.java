package com.arshan.it.ltd.messageing;

import javax.jms.ConnectionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

@Configuration
public class MessagingConfig {
	
	@Autowired
	private ConnectionFactory connectionFactory;
	
	@Value("${myqueue}")
	private String myQueue;
	
	@Bean
	public DefaultMessageListenerContainer messageListener()
	{
		DefaultMessageListenerContainer defaultMessageListenerContainer = new DefaultMessageListenerContainer();
		defaultMessageListenerContainer.setConnectionFactory(this.connectionFactory);
		defaultMessageListenerContainer.setDestinationName(myQueue);
		defaultMessageListenerContainer.setMessageListener(new Consumer());
		
		return defaultMessageListenerContainer;
	}

}
