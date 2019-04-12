package com.blute.att.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blute.att.domain.Attendance;
import com.blute.att.domain.User;


public interface AttendanceService {


	Attendance save(Attendance attendance);
	
	Attendance getAttendanceByEmail(String email);

}
