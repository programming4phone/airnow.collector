package com.programming4phone.airnow.collector.entity;

import java.io.Serializable;

public class CurrentAirQuality implements Serializable{

	private static final long serialVersionUID = 1072867041266532678L;
	private String zipCode;
	private String city;
	private String state;
	private Double latitude;
	private Double longitude;
	private Integer ozoneAQI;
	private Integer particulateMatterAQI;
	
	public String getZipCode() {
		return zipCode;
	}
	public CurrentAirQuality setZipCode(String zipCode) {
		this.zipCode = zipCode;
		return this;
	}
	public String getCity() {
		return city;
	}
	public CurrentAirQuality setCity(String city) {
		this.city = city;
		return this;
	}
	public String getState() {
		return state;
	}
	public CurrentAirQuality setState(String state) {
		this.state = state;
		return this;
	}
	public Double getLatitude() {
		return latitude;
	}
	public CurrentAirQuality setLatitude(Double latitude) {
		this.latitude = latitude;
		return this;
	}
	public Double getLongitude() {
		return longitude;
	}
	public CurrentAirQuality setLongitude(Double longitude) {
		this.longitude = longitude;
		return this;
	}
	public Integer getOzoneAQI() {
		return ozoneAQI;
	}
	public CurrentAirQuality setOzoneAQI(Integer ozoneAQI) {
		this.ozoneAQI = ozoneAQI;
		return this;
	}
	public Integer getParticulateMatterAQI() {
		return particulateMatterAQI;
	}
	public CurrentAirQuality setParticulateMatterAQI(Integer particulateMatterAQI) {
		this.particulateMatterAQI = particulateMatterAQI;
		return this;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CurrentAirQuality other = (CurrentAirQuality) obj;
		if (zipCode == null) {
			if (other.zipCode != null)
				return false;
		} else if (!zipCode.equals(other.zipCode))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "CurrentAirQuality [zipCode=" + zipCode + ", city=" + city + ", state=" + state + ", latitude="
				+ latitude + ", longitude=" + longitude + ", ozoneAQI=" + ozoneAQI + ", particulateMatterAQI="
				+ particulateMatterAQI + "]";
	}
	
	
}
