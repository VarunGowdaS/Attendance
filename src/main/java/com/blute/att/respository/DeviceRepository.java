package com.blute.att.respository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blute.att.domain.Device;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long>{

	Page<Device> findAll(Pageable pageable); // This is used by DeviceResource controller to fetch all registered user devices.

	Device findOneByImei(Long imei); //find a single registered device 
	
	Device findByUserId(Long userId); // find a device by userId
	
	void deleteByUserId(Long userId); // remove device by id
	
	boolean findByImei(Long imei);
	
	//Page<Device> findOneByUserId(Long userId);

	//Device deleteDevice()

	
}
