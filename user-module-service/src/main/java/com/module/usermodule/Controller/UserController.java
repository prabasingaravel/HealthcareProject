package com.module.usermodule.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.module.usermodule.Dto.PatientVitalSignDto;
import com.module.usermodule.Dto.UserDto;
import com.module.usermodule.Model.User;
import com.module.usermodule.Service.PatientService;
import com.module.usermodule.Service.UserService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	public UserController(AuthenticationManager authenticationManager, UserService userService,
			PatientService patientService) {
		super();
		this.authenticationManager = authenticationManager;
		this.userService = userService;
		this.patientService = patientService;
	}

	private final AuthenticationManager authenticationManager;

	private final UserService userService;

	private final PatientService patientService;

	@PostMapping(path="/entry",consumes= {"application/json"})
	@ApiOperation(value = "Insert User Detail", response = User.class)
	public User addUser(@RequestBody UserDto userDto) {
		return userService.addUser(userDto);
	}

	@GetMapping(path="/detail/{userName}",produces= {"application/json"})
	@Cacheable(value = "user", key = "#userName")
	@ApiOperation(value = "Fetch Specific User Detail", response = User.class)
	public User getUserInfo(@PathVariable String userName) {
		return userService.getUserInfo(userName);
	}
	
	@GetMapping(path="/detail",produces= {"application/json"})
	@Cacheable(value = "user")
	@ApiOperation(value = "Fetch All User Detail", response = List.class)
	public List<User> getAllUserInfo() {
		return userService.getAllUserInfo();
	}
	
	@DeleteMapping(path="/delete/{userId}")
	@CacheEvict(value = "user", key = "#userId")
	@ApiOperation(value = "Delete Specific User Detail", response = String.class)
	public String DeleteUserInfo(@PathVariable long userId) {
		return userService.DeleteUserInfo(userId);
	}

	@PutMapping(path="/update", consumes= {"application/json"})
	@CachePut(value = "user", key="#userId")
	@ApiOperation(value = "Update User Detail", response = User.class)
	public User updateUserDetail(@RequestBody UserDto userDto) {
		return userService.updateUserDetail(userDto);
	}

	@PostMapping(path="/patient", consumes= {"application/json"})
	public PatientVitalSignDto addPatient(@RequestBody PatientVitalSignDto patientDto) {
		return patientService.addPatient(patientDto);
	}
	
	@GetMapping(path = "/patient/{patientId}", produces = {"application/json"})
	public PatientVitalSignDto getPatient(@PathVariable int patientId) {
		return patientService.getPatientById(patientId);
	}
	
	@PostMapping("/authenticate")
	@ApiOperation(value = "Insert authenticate Detail", response = String.class)
	public String generateToken(@RequestBody UserDto authRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword()));
		} catch (Exception e) {
			throw new Exception("invaild Username/Password");
		}
		return userService.tokenInfo(authRequest.getUserName(), authRequest.getPassword());
	}
}
