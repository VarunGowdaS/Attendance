package com.blute.att.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.blute.att.domain.enumeration.Availablestatus;

@Entity
@Table(name = "attendance")
public class Attendance implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "imei")
	private Long imei;

    @Column(name = "latitude", precision = 10, scale = 2)
    private BigDecimal latitude;

    @Column(name = "longitude", precision = 10, scale = 2)
    private BigDecimal longitude;
	
    @Column(name = "email")
    private String email;
    
    @Column(name = "status_time")
    private Date status_time;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "available_status")
    private Availablestatus availablestatus;
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public Attendance userId(String userId) {
        this.userId = userId;
        return this;
    }

    public void setUser_id(String userId) {
        this.userId = userId;
    }
    

    public Long getImei() {
		return imei;
	}

    public Attendance imei(Long imei) {
    	this.imei=imei;
    	return this;
    }
    
	public void setImei(Long imei) {
		this.imei = imei;
	}
	
    public BigDecimal getLatitude() {
        return latitude;
    }

    public Attendance latitude(BigDecimal latitude) {
        this.latitude = latitude;
        return this;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public Attendance longitude(BigDecimal longitude) {
        this.longitude = longitude;
        return this;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getStatus_time() {
		return status_time;
	}

	public void setStatus_time(Date status_time) {
		this.status_time = status_time;
	}

	public Availablestatus getAvailablestatus() {
		return availablestatus;
	}

	public void setAvailablestatus(Availablestatus availablestatus) {
		this.availablestatus = availablestatus;
	}
 
    
    
	
	
}
