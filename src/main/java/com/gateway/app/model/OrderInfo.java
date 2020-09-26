package com.gateway.app.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;

@Service
public class OrderInfo {
	private Long id;
	private int units, user;
	private String weight, volume, value, transportType, warehouse;
	private Date deliveryDate;

	public OrderInfo() {
	}


	public OrderInfo(Long id, int units, int user, String weight, String volume, String value, String transportType,
			String warehouse, Date deliveryDate) {
		super();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");	
		dateFormat.format(deliveryDate);
		dateFormat.format(this.deliveryDate);
		this.id = id;
		this.units = units;
		this.user = user;
		this.weight = weight;
		this.volume = volume;
		this.value = value;
		this.transportType = transportType;
		this.warehouse = warehouse;
		this.deliveryDate = deliveryDate;
		dateFormat.format(this.deliveryDate);
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public int getUnits() {
		return units;
	}


	public void setUnits(int units) {
		this.units = units;
	}


	public int getUser() {
		return user;
	}


	public void setUser(int user) {
		this.user = user;
	}


	public String getWeight() {
		return weight;
	}


	public void setWeight(String weight) {
		this.weight = weight;
	}


	public String getVolume() {
		return volume;
	}


	public void setVolume(String volume) {
		this.volume = volume;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public String getTransportType() {
		return transportType;
	}


	public void setTransportType(String transportType) {
		this.transportType = transportType;
	}


	public String getWarehouse() {
		return warehouse;
	}


	public void setWarehouse(String warehouse) {
		this.warehouse = warehouse;
	}


	public Date getDeliveryDate() {
		return deliveryDate;
	}


	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}


	@Override
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return String.format(
				"{\"id\":%d,\"weight\":\"%s\",\"volume\":\"%s\", \"value\":\"%s\", \"units\":%d, \"transportType\":\"%s\",\"user\":%d, \"deliveryDate\":\"%s\"}",
				id, weight, volume, value, units, transportType, user, dateFormat.format(deliveryDate));
	}

}
