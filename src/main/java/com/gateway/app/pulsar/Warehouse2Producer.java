package com.gateway.app.pulsar;

import org.apache.pulsar.client.api.Producer;
import org.apache.pulsar.client.api.PulsarClientException;
import org.apache.pulsar.client.api.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

@Service
public class Warehouse2Producer extends PulsarConfig {

	private final Logger logger = LoggerFactory.getLogger(Warehouse2Producer.class);
	private Producer<String> stringGatewayProducer;
	private String topicNameWarehouse2 = "GatewayToWarehouse2Topic";

	public Warehouse2Producer() {

		super();
		try {
			stringGatewayProducer = pulsarClient.newProducer(Schema.STRING).topic(topicNameWarehouse2).create();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

	@Override
	public void sendMessageToWarehouse(String message) {

		try {
			stringGatewayProducer.send(message);
			logger.info(String.format("Warehouse2 Producer (Pulsar), sent message: %s", message));
		} catch (Exception e) {
			logger.error(String.format("Warehouse2 Producer (Pulsar), Could not send message %s", e.getMessage()));
		}
	}

}
