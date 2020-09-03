package com.gateway.app.service;

import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;
import org.springframework.stereotype.Service;

@Service
public class ProducerPulsar {
	
	private PulsarClient pulsarClient; 
	private Producer<String> stringProducerPulsar;
	private String topicNamePulsar;
	
	public ProducerPulsar() {
		try {
			topicNamePulsar = "GatewayToWarehousePulsar";
			pulsarClient = PulsarClient.builder()
					.serviceUrl("pulsar://localhost:6650")
					.build();
			
			stringProducerPulsar = pulsarClient.newProducer(Schema.STRING)
					.topic(topicNamePulsar)
					.create();
		} catch (Exception e) {
			System.out.println("Could not create pulsar client" + e.getMessage());
		}				
	}
	
	
	public void sendMessageProducerPulsar(String message) {
		try {
			stringProducerPulsar.send(message);
			System.out.println("Pulsar Producer sent message: " + message);
		} catch (PulsarClientException e) {
			System.out.println("Could not send message " + e.getMessage());
		}
		
	}
	
	

}
