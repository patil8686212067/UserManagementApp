package com.nil.usermgmt.utility;

public class UserRequestDTO {
	
	private String email;
	private String confirmPassword;
	private String newPassword;
	private String tempPassword;
	private String userAccountStatus;
	
	
	public String getUserAccountStatus() {
		return userAccountStatus;
	}
	public void setUserAccountStatus(String userAccountStatus) {
		this.userAccountStatus = userAccountStatus;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getTempPassword() {
		return tempPassword;
	}
	public void setTempPassword(String tempPassword) {
		this.tempPassword = tempPassword;
	}
	

}
