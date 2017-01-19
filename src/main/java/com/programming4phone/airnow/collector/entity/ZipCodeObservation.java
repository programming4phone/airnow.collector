package com.programming4phone.airnow.collector.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ZipCodeObservation {
	
	@JsonProperty("DateObserved")
	private String dateObserved;
	
	@JsonProperty("HourObserved")
	private Integer hourObserved;
	
	@JsonProperty("LocalTimeZone")
	private String localTimeZone;
	
	@JsonProperty("ReportingArea")
	private String reportingArea;
	
	@JsonProperty("StateCode")
	private String stateCode;
	
	@JsonProperty("Latitude")
	private Double latitude;
	
	@JsonProperty("Longitude")
	private Double longitude;
	
	@JsonProperty("ParameterName")
	private String parameterName;
	
	@JsonProperty("AQI")
	private Integer aqi;
	
	@JsonProperty("Category")
	private ObservationCategory category;
	
	public String getDateObserved() {
		return dateObserved;
	}
	public ZipCodeObservation setDateObserved(String dateObserved) {
		this.dateObserved = dateObserved;
		return this;
	}
	public Integer getHourObserved() {
		return hourObserved;
	}
	public ZipCodeObservation setHourObserved(Integer hourObserved) {
		this.hourObserved = hourObserved;
		return this;
	}
	public String getLocalTimeZone() {
		return localTimeZone;
	}
	public ZipCodeObservation setLocalTimeZone(String localTimeZone) {
		this.localTimeZone = localTimeZone;
		return this;
	}
	public String getReportingArea() {
		return reportingArea;
	}
	public ZipCodeObservation setReportingArea(String reportingArea) {
		this.reportingArea = reportingArea;
		return this;
	}
	public String getStateCode() {
		return stateCode;
	}
	public ZipCodeObservation setStateCode(String stateCode) {
		this.stateCode = stateCode;
		return this;
	}
	public Double getLatitude() {
		return latitude;
	}
	public ZipCodeObservation setLatitude(Double latitude) {
		this.latitude = latitude;
		return this;
	}
	public Double getLongitude() {
		return longitude;
	}
	public ZipCodeObservation setLongitude(Double longitude) {
		this.longitude = longitude;
		return this;
	}
	public String getParameterName() {
		return parameterName;
	}
	public ZipCodeObservation setParameterName(String parameterName) {
		this.parameterName = parameterName;
		return this;
	}
	public Integer getAqi() {
		return aqi;
	}
	public ZipCodeObservation setAqi(Integer aqi) {
		this.aqi = aqi;
		return this;
	}
	public ObservationCategory getCategory() {
		return category;
	}
	public ZipCodeObservation setCategory(ObservationCategory category) {
		this.category = category;
		return this;
	}
	@Override
	public String toString() {
		return "ZipCodeObservation [dateObserved=" + dateObserved + ", hourObserved=" + hourObserved
				+ ", localTimeZone=" + localTimeZone + ", reportingArea=" + reportingArea + ", stateCode=" + stateCode
				+ ", latitude=" + latitude + ", longitude=" + longitude + ", parameterName=" + parameterName + ", aqi="
				+ aqi + ", category=" + category + "]";
	}
	
	
}
