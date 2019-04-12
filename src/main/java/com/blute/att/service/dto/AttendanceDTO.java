package com.blute.att.service.dto;

import com.blute.att.domain.Device;

import java.math.BigDecimal;
import java.util.Date;

import com.blute.att.domain.Attendance;

public class AttendanceDTO {

	private Attendance trackuser;
	
	private Device device;
	/*
	 * private BigDecimal latitude;
	 * 
	 * private BigDecimal longitude;
	 * 
	 * private Date time;
	 */
	
	public AttendanceDTO(Attendance trackuser, Device device) {
		super();
		this.trackuser = trackuser;
		this.device = device;
	}

	public Attendance getAttendance() {
		return trackuser;
	}

	public void setAttendance(Attendance trackuser) {
		this.trackuser = trackuser;
	}

	public Device getDevice() {
		return device;
	}

	public void setDevice(Device device) {
		this.device = device;
	}

	public Attendance getTrackuser() {
		return trackuser;
	}

	public void setTrackuser(Attendance trackuser) {
		this.trackuser = trackuser;
	}

	/*
	 * public BigDecimal getLongitude() { return longitude; }
	 * 
	 * public void setLongitude(BigDecimal longitude) { this.longitude = longitude;
	 * }
	 * 
	 * public Date getTime() { return time; }
	 * 
	 * public void setTime(Date time) { this.time = time; }
	 */
	
	
	
	
	
	
}
