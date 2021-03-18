package com.nil.usermgmt.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nil.usermgmt.entity.City;
import com.nil.usermgmt.entity.State;

@Repository
public interface CityMasterRepository extends JpaRepository<City, Integer> {

	
	@Query("SELECT CT.cityId, CT.cityName from City CT where CT.stateId=:stateId")
	List<City> getCitiesByStateId(Integer stateId);
}
