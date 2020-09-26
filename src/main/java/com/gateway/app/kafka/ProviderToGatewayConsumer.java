package com.gateway.app.kafka;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.gateway.app.service.WarehouseRoute;

@Service
public class ProviderToGatewayConsumer {

	private final Logger logger = LoggerFactory.getLogger(ProviderToGatewayConsumer.class);
	@Autowired
	private WarehouseRoute warehouseRoute;

	public ProviderToGatewayConsumer() {
	}

	@KafkaListener(topics = "ProviderToGatewayTopic", groupId = "group_id")
	public void consume(String message) throws IOException {
		logger.info(String.format("#### -> Gateway Consumer (Kafkfa). Consumed message -> %s", message));
		warehouseRoute.processAndSplitMessage(message);
	}

}
