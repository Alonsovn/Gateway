package com.gateway.app.service;

import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@ConfigurationProperties(prefix = "pulsar-config")
@Configuration
@Service
public class Warehouse2Producer extends GatewayPulsarClient {

	private Producer<String> stringGatewayProducer;
	private String gatewayToWarehouse2Topic = "GatewayToWarehouse2Topic";
	

	public Warehouse2Producer() {
		super();
		try {
			stringGatewayProducer = pulsarClient.newProducer(Schema.STRING).topic(gatewayToWarehouse2Topic).create();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public String getTopicGatewayToWarehouse1() {
		return gatewayToWarehouse2Topic;
	}

	public void setTopicGatewayToWarehouse1(String topicGatewayToWarehouse1) {
		this.gatewayToWarehouse2Topic = topicGatewayToWarehouse1;
	}

	@Override
	public void sendMessageToWarehouse(String message) {
		try {
			stringGatewayProducer.send(message);
			System.out.println("Warehouse2 Producer (Pulsar), sent message: " + message);
		} catch (PulsarClientException e) {
			System.out.println("Warehouse2 Producer (Pulsar), Could not send message " + e.getMessage());
		}
	}

}
