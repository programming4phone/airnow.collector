package com.programming4phone.airnow.collector.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ObservationCategory {
	
	@JsonProperty("Number")
	private String number;
	
	@JsonProperty("Name")
	private String name;
	
	public String getNumber() {
		return number;
	}
	public ObservationCategory setNumber(String number) {
		this.number = number;
		return this;
	}
	public String getName() {
		return name;
	}
	public ObservationCategory setName(String name) {
		this.name = name;
		return this;
	}
	@Override
	public String toString() {
		return "ObservationCategory [number=" + number + ", name=" + name + "]";
	}
	
	
	
}
