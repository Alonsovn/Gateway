package com.gateway.app.service;

import org.apache.pulsar.client.api.PulsarClient;
import org.springframework.stereotype.Service;


@Service
public class GatewayPulsarClient {

	protected PulsarClient pulsarClient;
	private String serviceUrl = "pulsar://localhost:6650";

	public GatewayPulsarClient() {
		try {
			pulsarClient = PulsarClient.builder().serviceUrl(serviceUrl).build();
		} catch (Exception e) {
			System.out.println("Could not create pulsar client" + e.getMessage());
		}
	}

	public String getServiceUrl() {
		return serviceUrl;
	}

	public void setServiceUrl(String serviceUrl) {
		this.serviceUrl = serviceUrl;
	}

	public void sendMessageToWarehouse(String message) {
	}

}
