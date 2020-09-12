package com.gateway.app.service;

import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;
import org.springframework.stereotype.Service;

//@ConfigurationProperties(prefix = "pulsar-config")
//@Configuration
@Service
public class Warehouse1Producer extends GatewayPulsarClient {

	private Producer<String> stringGatewayProducer;
	private String gatewayToWarehouse1Topic = "GatewayToWarehouse1Topic";
	

	public Warehouse1Producer() {
		super();
		try {
			stringGatewayProducer = pulsarClient.newProducer(Schema.STRING).topic(gatewayToWarehouse1Topic).create();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getTopicGatewayToWarehouse1() {
		return gatewayToWarehouse1Topic;
	}

	public void setTopicGatewayToWarehouse1(String topicGatewayToWarehouse1) {
		this.gatewayToWarehouse1Topic = topicGatewayToWarehouse1;
	}

	@Override
	public void sendMessageToWarehouse(String message) {
		try {
			stringGatewayProducer.send(message);
			System.out.println("Warehouse1 Producer (Pulsar), sent message: " + message);
		} catch (PulsarClientException e) {
			System.out.println("Warehouse1 Producer (Pulsar), Could not send message " + e.getMessage());
		}
	}

}
