package com.nil.usermgmt.repository;

import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.nil.usermgmt.entity.Country;
@Repository
public interface CountryMasterRepository extends JpaRepository<Country, Serializable> {
	
	
	//Native SQl Query to where in select clause Column Name should entity field name(propertyName)
	// in where clause table name should be Entity Name
	
	@Query("SELECT CNT.countryId, CNT.countryName from Country CNT")
    List<Country> findAllCountries();

}
