package com.blute.att.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blute.att.domain.Attendance;
import com.blute.att.domain.enumeration.Availablestatus;
import com.blute.att.respository.AttendanceRepository;
import com.blute.att.respository.UserRepository;

@Service
@Transactional
public class AttendanceServiceImpl implements AttendanceService {

	@Autowired
	private AttendanceRepository attendanceRepository;

	@Override
	public Attendance getAttendanceByEmail(String email) {

		return attendanceRepository.findByEmailIgnoreCase(email);
	}

	@Autowired
	UserRepository userRepository;

	@Override
	public Attendance save(Attendance attendance) {
		attendance.setLatitude(attendance.getLatitude());
		attendance.setLongitude(attendance.getLongitude());
		if (attendance.getAvailablestatus() == null) {
			attendance.setAvailablestatus(Availablestatus.ONLINE);
			attendance.setStatus_time(new Date());
		} else if (attendance.getAvailablestatus() == Availablestatus.ONLINE) {
			attendance.setAvailablestatus(Availablestatus.OFFLINE);
			attendance.setStatus_time(new Date());
		}
			
		return attendanceRepository.save(attendance);
	}
}
