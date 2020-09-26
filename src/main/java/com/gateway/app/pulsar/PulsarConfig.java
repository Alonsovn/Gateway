package com.gateway.app.pulsar;

import org.apache.pulsar.client.api.PulsarClient;
import org.apache.pulsar.client.api.PulsarClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

//@ConfigurationProperties(prefix = "pulsar-config")
@Service
public class PulsarConfig {

	private final Logger logger = LoggerFactory.getLogger(PulsarConfig.class);
	protected PulsarClient pulsarClient;
	private String serviceUrl = "pulsar://localhost:6650";

	public PulsarConfig() {
		try {
			pulsarClient = PulsarClient.builder().serviceUrl(serviceUrl).build();
			logger.info("PulsarClient created");
		} catch (PulsarClientException e) {
			logger.error(String.format("Could not create pulsar client %s", e.getMessage()));
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
