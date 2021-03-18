package com.nil.usermgmt.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="USER_DTLS")
public class User {
	
	@Id
	@Column(name="USER_ID")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer userId;
	
	@Column(name="USER_EMAIL")
	private String email;
	
	@Column(name="USER_MOBILE")
	private String userMobileNo;
	
	@Column(name="GENDER")
	private String gender;
	
	@Column(name="COUNTRY_ID")
	private Integer countryID;
	
	@Column(name="STATE_ID")
	private Integer stateID;
	
	@Column(name="CITY_ID")
	private Integer cityId;
	
	@Column(name="CREATED_DATE")
	private LocalDateTime createdDate;
	
	@Column(name="UPDATED_DATE")
	private LocalDateTime updatedDate;
	
	@Column(name="DOB")
	private Date dateOfBirth;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="USER_PWD")
	private String password;
	
	@Column(name="ACC_STATUS")
	private String useAccountStatus;
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserMobileNo() {
		return userMobileNo;
	}

	public void setUserMobileNo(String userMobileNo) {
		this.userMobileNo = userMobileNo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Integer getCountryID() {
		return countryID;
	}

	public void setCountryID(Integer countryID) {
		this.countryID = countryID;
	}

	public Integer getStateID() {
		return stateID;
	}

	public void setStateID(Integer stateID) {
		this.stateID = stateID;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime localDateTime) {
		this.createdDate = localDateTime;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime localDateTime) {
		this.updatedDate = localDateTime;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUseAccountStatus() {
		return useAccountStatus;
	}

	public void setUseAccountStatus(String useAccountStatus) {
		this.useAccountStatus = useAccountStatus;
	}


}
