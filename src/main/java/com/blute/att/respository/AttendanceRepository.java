package com.blute.att.respository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blute.att.domain.Attendance;
import com.blute.att.domain.User;
import com.blute.att.domain.enumeration.Availablestatus;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {

	Page<Attendance> findAllByUserId(String userId,Pageable pageable);
	
	Attendance findByEmailIgnoreCase(String email);
	/*
	 * Page<Attendance> findAllAvalilableStatus(String str, Pageable pageable);
	 */
}
