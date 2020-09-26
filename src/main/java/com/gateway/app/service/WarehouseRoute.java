package com.gateway.app.service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gateway.app.model.OrderInfo;
import com.gateway.app.pulsar.Warehouse1Producer;
import com.gateway.app.pulsar.Warehouse2Producer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

@Service
public class WarehouseRoute {

	private final Logger logger = LoggerFactory.getLogger(WarehouseRoute.class);
	@Autowired
	private Warehouse1Producer warehouseProducer1;
	@Autowired
	private Warehouse2Producer warehouseProducer2;

	public WarehouseRoute() {
	}

	public void processAndSplitMessage(String message) {

		// Gson gson = new Gson();
		Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy").create();
		Type type = new TypeToken<List<OrderInfo>>() {
		}.getType();
		List<OrderInfo> orderInfoList = gson.fromJson(message, type);
		ArrayList<OrderInfo> warehouseList1 = new ArrayList<OrderInfo>();
		ArrayList<OrderInfo> warehouseList2 = new ArrayList<OrderInfo>();

		for (OrderInfo orderInfo : orderInfoList) {
			if (orderInfo.getWarehouse().equals("Warehouse 1")) {
				warehouseList1.add(orderInfo);
			} else if (orderInfo.getWarehouse().equals("Warehouse 2")) {
				warehouseList2.add(orderInfo);
			}
		}

		if (warehouseList1.size() > 0) {
			sendMessageToWarehouse1(warehouseList1.toString());
		}

		if (warehouseList2.size() > 0) {
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
