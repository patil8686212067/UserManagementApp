package com.nil.usermgmt.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nil.usermgmt.entity.User;
import com.nil.usermgmt.service.UserServiceImpl;
import com.nil.usermgmt.utility.FronEndUrlProperties;
import com.nil.usermgmt.utility.UserRequestDTO;

//@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserManagementController {

	private static final Logger logger = LoggerFactory.getLogger(UserManagementController.class);

	@Autowired
	private UserServiceImpl userServiceImpl;

	@Autowired
	private FronEndUrlProperties fronEndUrlProperties;

	/**
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<?> registerUser(@RequestBody User user) {
		logger.debug("**** register() - Execution Started **** ");

		try {
			boolean isSaved = userServiceImpl.saveUser(user);
			if (isSaved) {

				String succMsg = "User Registered Successfully..!!";
				return new ResponseEntity<>(succMsg, HttpStatus.CREATED);
			}
		} catch (Exception e) {
			logger.error(" ** Exception Occured : ** " + e);
		}
		logger.debug("**** register() - Execution Ended**** ");

		String failMsg = "Failed to Register User ..!!";

		return new ResponseEntity<>(failMsg, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	/**
	 * @return
	 */
	/*@RequestMapping(value = "/getAllCountries", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<Integer, String>> getCountries() {
		Map<Integer, String> mapObj = new HashMap<Integer, String>();
		mapObj = userServiceImpl.getAllCountries();
			
		return new ResponseEntity<Map<Integer, String>>(mapObj, HttpStatus.OK);

	}*/
	
	@RequestMapping(value = "/getAllCountries", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody String getStatesByCountryId(@RequestParam Integer countryId) {
		String json=null;
		List<Object[]> list = (List<Object[]>) userServiceImpl.getAllCountries();
		try {
			json=new ObjectMapper().writeValueAsString(list);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

	/**
	 * @param countryId
	 * @return
	 */
	@RequestMapping(value = "/getstates/{countryId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<Integer, String>> getStateByCountryId(@PathVariable("countryId") Integer countryId) {

		Map<Integer, String> mapObj = new HashMap<Integer, String>();
		mapObj = userServiceImpl.getStatesByCountryId(countryId);

		return new ResponseEntity<Map<Integer, String>>(mapObj, HttpStatus.OK);

	}

	/**
	 * @param stateId
	 * @return
	 */
	@RequestMapping(value = "/getcities/{stateId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<Integer, String>> getCityByStateId(@PathVariable("stateId") Integer stateId) {

		Map<Integer, String> mapObj = new HashMap<Integer, String>();
		mapObj = userServiceImpl.getCitiesByStateId(stateId);

		return new ResponseEntity<Map<Integer, String>>(mapObj, HttpStatus.OK);

	}

	/**
	 * @param user
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestBody User user) {
		logger.debug("**** login() - Execution Started **** " + user.getEmail() + "********" + user.getPassword());
		String responseMsg = "";

		try {

			responseMsg = userServiceImpl.loginCheck(user.getEmail(), user.getPassword());

			return new ResponseEntity<>(responseMsg, HttpStatus.CREATED);

		} catch (Exception e) {
			logger.error(" ** Exception Occured : ** " + e);
			return new ResponseEntity<>(responseMsg, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	/**
	 * @param emailId
	 * @return
	 */
	@RequestMapping(value = "/forgotpassword/{email}", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> forgotPassword(@PathVariable("email") String email) {

		String respMessage = "";
		try {

			respMessage = userServiceImpl.forgotPassword(email);
			return new ResponseEntity<>(respMessage, HttpStatus.CREATED);

		} catch (Exception e) {
			return new ResponseEntity<>(respMessage, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	// on ajax onclick event we need to call email validation

	@RequestMapping(value = "/validate/{email}", method = RequestMethod.GET)
	public ResponseEntity<?> validateEmail(@PathVariable("email") String email) {
		logger.debug("**** validateEmail() - Execution Started **** ");
		boolean responseStatus = false;

		try {

			responseStatus = userServiceImpl.isEmailUnique(email);

			return new ResponseEntity<>(responseStatus, HttpStatus.CREATED);

		} catch (Exception e) {
			logger.error(" ** Exception Occured : ** " + e);
			return new ResponseEntity<>(responseStatus, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// on email link click this api should called 'Link to Unlock account'
	// we should redirect to froneEnd Unlock Account screen

	@RequestMapping(value = "/unlockaccount", method = RequestMethod.GET)
	public void unlockAccountLink(HttpServletResponse response, HttpServletRequest request) throws IOException {

		logger.info("In side unlockAccountLink() ");
		System.out.print("url for front end-->" + request.getHeader("origin"));
		System.out.print("your fronENd url " + fronEndUrlProperties.getFrontEndHost());
		response.sendRedirect("http://localhost:4200" + "/unlockaccountLinkpage");

	}

	// unlock form Submission handler method

	@RequestMapping(value = "/unlockaccountsubmit", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<String> unlockUserAccount(@RequestBody UserRequestDTO userReqObj) {

		boolean isAccountUnlock = false;
		try {
			
			isAccountUnlock = userServiceImpl.unlockUserAccount(userReqObj);

			if(isAccountUnlock==true)
			{
				return new ResponseEntity<>("Account is unlocked, please proceed with login", HttpStatus.CREATED);

			}
			
		} catch (Exception e)
		{
			 logger.error(" ** Exception Occured : ** " +e);

		}
		return new ResponseEntity<>("unable to unlock Account becauuse temp password is Invalid ",
				HttpStatus.INTERNAL_SERVER_ERROR);

	}
}
