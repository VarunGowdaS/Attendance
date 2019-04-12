package com.blute.att.rest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blute.att.domain.User;
import com.blute.att.jwtsecurity.JwtTokenUtil;
import com.blute.att.jwtsecurity.JwtUser;
import com.blute.att.rest.errors.UnauthorizedException;
import com.blute.att.service.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonProperty;


//@CrossOrigin(value = "192.168.0.22:4200")
@RestController
//@RequestMapping(value = "/api")
public class UserAuthenticationController {

	
	@Value("${jwt.header}")
	private String tokenHeader;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@PostMapping(value = "/login")
	public ResponseEntity<UserDTO> login(@RequestBody User user, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse ){
			try {
				Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()/*, user.getDevice().getImei()*/));
				final JwtUser userDetails = (JwtUser)authentication.getPrincipal();
				SecurityContextHolder.getContext().setAuthentication(authentication);
				final String token = jwtTokenUtil.generateToken(userDetails);
				httpServletResponse.setHeader("token", token);
				return new ResponseEntity<UserDTO>(new UserDTO(userDetails.getUser(),token), HttpStatus.OK);
				
			} catch (Exception e) {
				throw new UnauthorizedException(e.getMessage());
			}
	}
	

    /**
     * Object to return as body in JWT Authentication.
     */
    static class JWTToken {

        private String idToken;

        JWTToken(String idToken) {
            this.idToken = idToken;
        }

        @JsonProperty("id_token")
        String getIdToken() {
            return idToken;
        }

        void setIdToken(String idToken) {
            this.idToken = idToken;
        }
    }
	
}
