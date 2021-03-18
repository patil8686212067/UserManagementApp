package com.nil.usermgmt.service;

import java.util.Map;

import com.nil.usermgmt.entity.User;
import com.nil.usermgmt.utility.UserRequestDTO;

public interface UserService {
	
	public boolean saveUser(User user);
	
	public boolean isEmailUnique(String email);
	
	public String loginCheck(String email,String password);//check email and account active validation
	
	public String forgotPassword(String emailId);
    
	public boolean isTempPwdValid(String email,String tempPwd);
	
	public boolean unlockUserAccount(UserRequestDTO userAcctUnlock);
	
	public User findUserByEmail(String email);
	
	public Map<Integer,String> getAllCountries();
	
	public Map<Integer,String> getStatesByCountryId(Integer countryId);

	public Map<Integer,String> getCitiesByStateId(Integer countryId);
	

}
