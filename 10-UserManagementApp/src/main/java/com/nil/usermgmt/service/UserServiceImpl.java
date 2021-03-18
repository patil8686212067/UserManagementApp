package com.nil.usermgmt.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nil.usermgmt.entity.City;
import com.nil.usermgmt.entity.Country;
import com.nil.usermgmt.entity.State;
import com.nil.usermgmt.entity.User;
import com.nil.usermgmt.repository.CityMasterRepository;
import com.nil.usermgmt.repository.CountryMasterRepository;
import com.nil.usermgmt.repository.StateMasterRepository;
import com.nil.usermgmt.repository.UserAccountRepository;
import com.nil.usermgmt.utility.AES256;
import com.nil.usermgmt.utility.AppUtil;
import com.nil.usermgmt.utility.EmailUtil;
import com.nil.usermgmt.utility.UserRequestDTO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Autowired
	private CountryMasterRepository countryMasterRepository;

	@Autowired
	private StateMasterRepository stateMasterRepository;
	
	@Autowired
	private CityMasterRepository cityMasterRepository;
	
	@Autowired
	private EmailUtil emailUtil;
	  
	
	  
	@Override
	public boolean saveUser(User user) {
		boolean isUserCreted = false;
		boolean isEmailExist =false;
		
		
		//check email existing in db ,if it is duplicate email already 
		//exist
		isEmailExist = isEmailUnique(user.getEmail());
		if(isEmailExist)
		{
			
			
			String password = AppUtil.generateRandomString(7);
			
			String encryptedPwd = AES256.encrypt(password);
			user.setPassword(encryptedPwd);
			user.setUseAccountStatus("Locked");
			user.setCreatedDate(LocalDateTime.now());
			user.setUpdatedDate(LocalDateTime.now());
			
			User userobj = userAccountRepository.save(user);
			
			if (userobj != null)
			{
				
				isUserCreted = true;
				
				String subject = "Unlock Account";
				String text = "<html><body> Hi, " + userobj.getFirstName() + " " + userobj.getLastName() + ": "
						+ "<br><b>Welcome to Ashok IT.,</b> Your registeration is almost complete."
						+ "<br>Please unlock your account using below details." + " <br><br>Your Temporary Password : " + encryptedPwd
						+ "<br><a href='http://localhost:8080/unlockaccount'>Click here to unlock account</a></body></html>";

				emailUtil.send(userobj.getEmail(), text, subject);				
			} 

		
		}else {
			
			isUserCreted = false;

		}
		return isUserCreted;
	}

	@Override
	public boolean isEmailUnique(String email) {

		boolean isUnique = false;

		User user = userAccountRepository.findUserByEmail(email);
		if (user == null) 
		{
			isUnique = true;

		} else {
			isUnique = false;

		}

		return isUnique;
	}

	@Override
	public String loginCheck(String email, String password) {

		String responseMessage = "";

		User user = userAccountRepository.findUserByEmailAndPassword(email, password);

		if (user != null) 
		{

			if (user.getUseAccountStatus().equals("UNLOCK")) {
				responseMessage = "Login Successfully";
			} else {
				responseMessage = "Account is Locked";
			}

		} else {
			responseMessage = "invalid Credentials .!";
		}

		return responseMessage;
	}

	@Override
	public Map<Integer, String> getAllCountries() {

		List<Country> countryList = countryMasterRepository.findAllCountries();

		HashMap<Integer, String> countryMap = new HashMap<Integer, String>();

		for (Country obj : countryList) {
			Integer countryId = obj.getCountryId();
			String countryName = obj.getCountryName();
			countryMap.put(countryId, countryName);
		}

		return countryMap;
	}

	@Override
	public Map<Integer, String> getStatesByCountryId(Integer countryId) {

		List<State> stateList = stateMasterRepository.getStatesByCountryId(countryId);

		HashMap<Integer, String> stateMap = new HashMap<Integer, String>();

		for (State obj : stateList) {
			Integer stateId = obj.getStateId();
			String stateName = obj.getStateName();
			stateMap.put(stateId, stateName);
		}

		return stateMap;
	}

	@Override
	public Map<Integer, String> getCitiesByStateId(Integer stateId) {

		List<City> cityList = cityMasterRepository.getCitiesByStateId(stateId);

		HashMap<Integer, String> cityMap = new HashMap<Integer, String>();

		for (City obj : cityList) {
			Integer cityId = obj.getCityId();
			String cityName = obj.getCityName();
			cityMap.put(cityId, cityName);
		}

		return cityMap;
	}

	@Override
	public String forgotPassword(String emailId) {
		
		String respMessage="";
		
		User user = userAccountRepository.findUserByEmail(emailId);
		if(user!=null) 
		{
			//generate Password
			String password = AppUtil.generateRandomString(7);
			
			String encryptedPwd = AES256.encrypt(password);
			userAccountRepository.updatepassword(encryptedPwd,user.getEmail());
			
			respMessage="Password updated Successfully !!";
		}else {
			respMessage="User Not Exits With Specified Email !!";
		}
		
		return respMessage;
	}

	@Override
	public boolean isTempPwdValid(String email, String tempPwd) {
		
		boolean isTempPwdValid = false;

		User user = userAccountRepository.findUserByEmailAndPassword(email, tempPwd);
		if (user != null) {
			isTempPwdValid = true;

		} else {
			isTempPwdValid = false;
		}

		return isTempPwdValid;
	}

	//after clciking on 'Link to unlock account' we should navigate here
	@Transactional
	public boolean unlockUserAccount(UserRequestDTO userAcctUnlock) {
		
		boolean isAccountActivated=false;
		boolean isTempPasswordValid =false;
		int acitiveUserCount =0;
		
		//validate temp password which is received at registrtaion  from mail and temp password of db
		isTempPasswordValid  = isTempPwdValid(userAcctUnlock.getEmail(),userAcctUnlock.getTempPassword());
		
		//check confirm pwd and new same pwd are same or not 
		
		if(isTempPasswordValid==true) 
		{
			 userAcctUnlock.setUserAccountStatus("UNLOCK");
			 acitiveUserCount = userAccountRepository.updateAccountStatus(userAcctUnlock.getUserAccountStatus(),userAcctUnlock.getEmail());

		 }
		if(acitiveUserCount==1) 
		{
			isAccountActivated=true;	
		}else {
			isAccountActivated=false;
		}
		return isAccountActivated;
	}

	@Override
	public User findUserByEmail(String email) {
		User user = userAccountRepository.findUserByEmail(email);
		return user;
	}
   
}
