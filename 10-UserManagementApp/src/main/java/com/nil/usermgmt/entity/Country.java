package com.nil.usermgmt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="COUNTRY_MASTER")
public class Country {
	
	@Id
	@Column(name="COUNTRY_ID")
	private Integer countryId;
	
	
	@Column(name="COUNTRY_CODE")
	private String countrCode;
	
	@Column(name="COUNTRY_NAME")
	private String countryName;
	

	public Integer getCountryId() {
		return countryId;
	}

	public void setCountryId(Integer countryId) {
		this.countryId = countryId;
	}

	public String getCountrCode() {
		return countrCode;
	}

	public void setCountrCode(String countrCode) {
		this.countrCode = countrCode;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	@Override
	public String toString() {
		return "Country [countryId=" + countryId + ", countrCode=" + countrCode + ", countryName=" + countryName + "]";
	}

}
