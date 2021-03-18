package com.nil.usermgmt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="CITIES_MASTER")
public class City {
	
	@Id
	@Column(name="CITY_ID")
	private Integer cityId;
	
	@Column(name="CITY_NAME")
	private String cityName;
	
	@Column(name="STATE_ID")
	private Integer stateId;

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	

	public Integer getStateId() {
		return stateId;
	}

	public void setStateId(Integer stateId) {
		this.stateId = stateId;
	}
	
}
