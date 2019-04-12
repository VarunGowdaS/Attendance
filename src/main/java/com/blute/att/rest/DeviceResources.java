package com.blute.att.rest;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blute.att.domain.Attendance;
import com.blute.att.domain.Device;
import com.blute.att.respository.DeviceRepository;
import com.blute.att.rest.util.HeaderUtil;
import com.blute.att.rest.util.PaginationUtil;
//import com.codahale.metrics.annotation.Timed;

@RestController
@RequestMapping("/api")
public class DeviceResources {

	@Autowired
	private DeviceRepository deviceRepository;

	private static final String ENTITY_NAME = "Device";

	/**
	 * get all registered 	
	 * @param pageable
	 * @return
	 */
	@GetMapping("/devices")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<Device>> getAllUserDevices(Pageable pageable) {
		Page<Device> page = deviceRepository.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page, "/api/devices");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	/**
	 * find a registered user device by userid
	 * @param userid
	 * @return
	 */
	@GetMapping("/devices/{userid}")
	public ResponseEntity<Device> getDeviceByUserId(@PathVariable Long userid) {

		Device device = deviceRepository.findByUserId(userid);
		return new ResponseEntity<>(device, HttpStatus.FOUND);
	}

	/**
	 * remove a registered device based on userid...
	 * @param userid
	 * @return
	 */
	@DeleteMapping("/devices/{userid}")
	// @Timed
	public ResponseEntity<Void> deleteTrackuser(@PathVariable Long userid) {
		// log.debug("REST request to delete Trackuser : {}", id);

		deviceRepository.deleteByUserId(userid);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, userid.toString()))
				.build();
	}

}
