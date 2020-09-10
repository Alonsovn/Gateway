package com.gateway.app.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.gateway.app.model.Provider;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

@Service
public class GatewayConsumer {

	private final Logger logger = LoggerFactory.getLogger(GatewayConsumer.class);
	@Autowired
	private Warehouse1Producer warehouseProducer1;
	@Autowired
	private Warehouse2Producer warehouseProducer2;

	public GatewayConsumer() {
		//warehouseProducer1 = new Warehouse1Producer();
	}

	@KafkaListener(topics = "ProviderToGatewayTopic", groupId = "group_id")
	public void consume(String message) throws IOException {
		logger.info(String.format("#### -> Gateway Consumer (Kafkfa). Consumed message -> %s", message));
		processAndSendMessage(message);

	}


	private void processAndSendMessage(String message) {
		Gson gson = new Gson();
		Type type = new TypeToken<List<Provider>>() {}.getType();
		List<Provider> providerList = gson.fromJson(message, type);
		ArrayList<Provider> warehouseList1 = new ArrayList<Provider>();
		ArrayList<Provider> warehouseList2 = new ArrayList<Provider>();
	
		for (Provider prov : providerList) {
			if (prov.getWarehouse().contains("1")) {
				warehouseList1.add(prov);
			} else if (prov.getWarehouse().contains("2")) {
				warehouseList2.add(prov);

			}
		}

		if (warehouseList1.size() > 0) {
			//System.out.println("\nPrintin list warehouse 1" + warehouse1.toString());
			sendMessageToWarehouse1(warehouseList1.toString());
		}

		if (warehouseList2.size() > 0) {
			//System.out.println("\nPrintin list warehouse 2" + warehouse2.toString());
			sendMessageToWarehouse2(warehouseList2.toString());
		}
	}

	public void sendMessageToWarehouse1(String message) {
		warehouseProducer1.sendMessageToWarehouse(message);

	}

	public void sendMessageToWarehouse2(String message) {
		warehouseProducer2.sendMessageToWarehouse(message);

	}

}
