package com.nil.usermgmt.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nil.usermgmt.entity.State;

@Repository
public interface StateMasterRepository extends JpaRepository<State, Serializable> {
	
	//wrong  hql implementaion modify as per required --done verify once
	@Query("SELECT ST.stateId, ST.stateName from State ST where ST.countryId=:countryId")
	List<State> getStatesByCountryId(Integer countryId);
 

}
