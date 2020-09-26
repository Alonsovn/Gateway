package com.gateway.app.pulsar;

import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Warehouse1Producer extends PulsarConfig {

	private final Logger logger = LoggerFactory.getLogger(Warehouse1Producer.class);

	private Producer<String> stringGatewayProducer;
	private String topicNameWarehouse1 = "GatewayToWarehouse1Topic";

	public Warehouse1Producer() {
		super();
		try {
			stringGatewayProducer = pulsarClient.newProducer(Schema.STRING).topic(topicNameWarehouse1).create();
			logger.info("Warehouse1Producer created.");
		} catch (PulsarClientException e) {
			logger.error(e.getMessage());
		}

	}

	@Override
	public void sendMessageToWarehouse(String message) {
		try {
			stringGatewayProducer.send(message);
			logger.info(String.format("Warehouse1 Producer (Pulsar), sent message: %s", message));
		} catch (PulsarClientException e) {
			logger.error(String.format("Warehouse1 Producer (Pulsar), Could not send message %s", e.getMessage()));
		}
	}

}
