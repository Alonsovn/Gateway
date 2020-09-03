package com.gateway.app.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class Consumer {

	private final Logger logger = LoggerFactory.getLogger(Consumer.class);
	private ProducerPulsar producerPulsar;
	
	
	public Consumer() {
		producerPulsar =  new ProducerPulsar();
	}
	

	@KafkaListener(topics = "topic", groupId = "group_id")
	public void consume(String message) throws IOException {
		logger.info(String.format("#### -> Consumed message (Kafkfa) -> %s", message));
		sendMessageProducerPulsar(message);
		

	}
	
	public void sendMessageProducerPulsar(String message) {
		producerPulsar.sendMessageProducerPulsar(message);
		
	}
	

}
