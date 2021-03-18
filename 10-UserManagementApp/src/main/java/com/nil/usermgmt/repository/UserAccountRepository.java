package com.nil.usermgmt.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nil.usermgmt.entity.User;

@Repository
public interface UserAccountRepository extends JpaRepository<User,Serializable>  {
	
	
	//select * from USER_DTLS where email =:email
	public User findUserByEmail(String email);
	
	//select * from USER_DTLS where email=:email and password=:password
    
	public User findUserByEmailAndPassword(String email,String password);
	
	
	//public String forgotPassword(String emailId);
    
	//public User isTempPwdValid(String email,String tempPwd);
	
	//public boolean unlockUserAccount(User userAcctUnlock);
	
	
	@Modifying
	@Query("UPDATE User SET useAccountStatus =:useAccountStatus WHERE email =:email")
	int updateAccountStatus(String useAccountStatus,String email);
	

	
	@Modifying
	@Query("UPDATE User u SET u.password =:passoword WHERE u.email =:email")
	int updatepassword(String passoword, String email);
	

}
