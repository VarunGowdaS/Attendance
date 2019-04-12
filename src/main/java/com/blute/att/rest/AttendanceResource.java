package com.blute.att.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.List;
//import java.util.Optional;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blute.att.domain.Device;
import com.blute.att.domain.User;
import com.blute.att.domain.enumeration.Availablestatus;
import com.blute.att.domain.Attendance;
import com.blute.att.respository.DeviceRepository;
import com.blute.att.respository.AttendanceRepository;
import com.blute.att.rest.errors.BadRequestAlertException;
import com.blute.att.rest.errors.DeviceNotRegisteredException;
import com.blute.att.rest.errors.DeviceRegistrationException;
import com.blute.att.rest.util.HeaderUtil;
import com.blute.att.rest.util.PaginationUtil;
import com.blute.att.service.AttendanceService;
import com.blute.att.service.dto.AttendanceDTO;

@RestController
@RequestMapping("/api")
public class AttendanceResource {

	private final Logger log = LoggerFactory.getLogger(AttendanceResource.class);

	private final static String ENTITY_NAME = "attendance";

	@Autowired
	private final AttendanceRepository attendanceRepository;

	@Autowired
	private final DeviceRepository deviceRepository;

	@Autowired
	private final AttendanceService attendanceService;

	public AttendanceResource(AttendanceRepository attendanceRepository, DeviceRepository deviceRepository,
			AttendanceService attendanceService) {
		this.attendanceRepository = attendanceRepository;
		this.deviceRepository = deviceRepository;
		this.attendanceService = attendanceService;
	}

	/**
	 * update a employee attendance
	 * 
	 * @param attendanceDTO
	 * @param session
	 * @return
	 * @throws URISyntaxException,
	 * @throws DeviceNotRegisteredException, if user has already registered a device
	 *                                       and trying to use a another device,
	 *                                       user should unregister the already
	 *                                       registered device to access from a new
	 *                                       device.
	 * 
	 */
	@PostMapping("/attendance")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Attendance> createUser(@RequestBody AttendanceDTO attendanceDTO, HttpSession session)
			throws URISyntaxException {
		log.debug("REST request to check device and save attendance: {}", attendanceDTO);
		Device device = deviceRepository.findByUserId(attendanceDTO.getDevice().getUserId());

		if (device != null && device.getImei().equals(attendanceDTO.getDevice().getImei())) {
			// Device deviveRegister = deviceRepository.save(attendanceDTO.getDevice());
			Attendance result = attendanceService.save(attendanceDTO.getAttendance());
			// session.setAttribute("email", attendanceDTO.getAttendance().getImei());
			return ResponseEntity.created(new URI("/api/attendances/" + result.getId()))
					.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);

		} else if (device == null) {
			Device deviceRegister = deviceRepository.save(attendanceDTO.getDevice());
			Attendance result = attendanceRepository.save(attendanceDTO.getAttendance());
			return ResponseEntity.created(new URI("/api/attendance/" + result.getId()))
					.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString())).body(result);

		} else {
			throw new DeviceNotRegisteredException();
		}
	}

	/**
	 * TODO::: update employee attendance such as logout time, logout latitude,
	 * logout longitude
	 * 
	 * @param attendanceDTO
	 * @return
	 * @throws URISyntaxException
	 */
	@PutMapping("/attendance")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Attendance> updateattendance(@RequestBody AttendanceDTO attendanceDTO, HttpSession session)
			throws URISyntaxException {
		/*
		 * Device device =
		 * deviceRepository.findByUserId(attendanceDTO.getDevice().getUserId()); if
		 * (device != null &&
		 * device.getImei().equals(attendanceDTO.getDevice().getImei())) {
		 */ // Device deviveRegister = deviceRepository.save(attendanceDTO.getDevice());
		log.debug("REST request to update attendance: {}", attendanceDTO);

		if (attendanceDTO.getAttendance().getId() == null) {
			throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
		}
		Attendance result = attendanceService.save(attendanceDTO.getAttendance());
		return ResponseEntity.ok().headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME, result.getId().toString()))
				.body(result);
	} /*
		 * else if (device != null && device.getImei() !=
		 * attendanceDTO.getDevice().getImei()) { throw new
		 * DeviceNotRegisteredException(); } else {
		 * 
		 * @SuppressWarnings("unused") Device deviceRegister =
		 * deviceRepository.save(attendanceDTO.getDevice()); Attendance result =
		 * attendanceRepository.save(attendanceDTO.getAttendance()); return
		 * ResponseEntity.created(new URI("/api/attendance/" + result.getId()))
		 * .headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME,
		 * result.getId().toString())).body(result); } }
		 */

	/**
	 * Only user with admin role can get access to all the user attendance
	 * 
	 * @param pageable
	 * @return list of user attendances
	 */
	@GetMapping("/attendances")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Attendance>> getAllattendances(Pageable pageable) {
		log.debug("REST request to get the page of all attendance");
		Page<Attendance> page = attendanceRepository.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/attendances");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	/**
	 * get attendance details for particular employee based on user id
	 * 
	 * @param userid
	 * @return
	 */
	// @SuppressWarnings("unchecked")
	@GetMapping("/attendance/{userid}")
	public ResponseEntity<List<Attendance>> getAttedance(@PathVariable String userid, Pageable pageable) {
		log.debug("REST request to get the page of attendance for UserId: {}", userid);
		Page<Attendance> attendance = attendanceRepository.findAllByUserId(userid, pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(attendance, "/api/attendance/{userid}");
		return new ResponseEntity<>(attendance.getContent(), headers, HttpStatus.OK);
	}

	@GetMapping(value = "/getattendance")
	@PreAuthorize("hasRole('USER')")
	public ResponseEntity<Attendance> getAttendance(Principal principal) {
		log.debug("REST request to get the attendance for the current user");
		Attendance att = attendanceService.getAttendanceByEmail(principal.getName());
		return new ResponseEntity<Attendance>(att, HttpStatus.OK);
	}

	/*
	 * @GetMapping(value = "/getonlineusers")
	 * 
	 * @PreAuthorize("hasRole('ADMIN')") public ResponseEntity<List<Attendance>>
	 * getAllOnline(Pageable pageable){ String str = "ONLINE"; Page<Attendance> page
	 * = attendanceRepository.findAllAvalilableStatus(str, pageable); HttpHeaders
	 * headers = PaginationUtil.generatePaginationHttpHeaders(page,
	 * "/api/attendance"); return new ResponseEntity<>(page.getContent(), headers,
	 * HttpStatus.OK); }
	 */
}
