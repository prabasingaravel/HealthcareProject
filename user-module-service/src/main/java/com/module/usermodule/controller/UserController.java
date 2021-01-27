package com.module.usermodule.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.module.usermodule.dto.AuthenticateDto;
import com.module.usermodule.dto.UserDto;
import com.module.usermodule.manager.TokenManager;
import com.module.usermodule.service.UserService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	public UserController(UserService userService, TokenManager tokenManager) {
		super();
		this.userService = userService;
		this.tokenManager = tokenManager;
	}
	
	private UserService userService;
	private TokenManager tokenManager;

	@PostMapping(path="/entry",consumes= {"application/json"})
	@ApiOperation(value = "Insert User Detail", response = UserDto.class)
	public UserDto addUser(@RequestBody UserDto userDto) {
		return userService.addUser(userDto);
	}

	@GetMapping(path="/detail/{userName}",produces= {"application/json"})
	@Cacheable(value = "user", key = "#userName")
	@ApiOperation(value = "Fetch Specific User Detail", response = UserDto.class)
	public UserDto getUserInfo(@PathVariable String userName) {
		return userService.getUserInfo(userName);
	}
	
	@GetMapping(path="/detail",produces= {"application/json"})
	@Cacheable(value = "user")
	@ApiOperation(value = "Fetch All User Detail", response = String.class)
	public String getAllUserInfo() {
		return userService.getAllUserInfo();
	}
	
	@DeleteMapping(path="/delete/{userId}",produces= {"application/json"})
	@CacheEvict(value = "user", key = "#userId")
	@ApiOperation(value = "Delete Specific User Detail", response = String.class)
	public String DeleteUserInfo(@PathVariable int userId) {
		return userService.DeleteUserInfo(userId);
	}

	@PutMapping("/update")
	@CachePut(value = "user", key="#userId")
	@ApiOperation(value = "Update User Detail", response = UserDto.class)
	public UserDto updateUserDetail(@RequestBody UserDto userDto) {
		return userService.updateUserDetail(userDto);
	}
	
	@PostMapping("/authenticate")
	@ApiOperation(value = "Insert authenticate Detail", response = AuthenticateDto.class)
	public AuthenticateDto generateToken(@RequestBody AuthenticateDto autheticateDto) throws Exception {
		return tokenManager.generateToken(autheticateDto);
	}
}
