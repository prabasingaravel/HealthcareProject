package com.module.usermodule.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.module.usermodule.Advice.TrackExecutionTime;
import com.module.usermodule.Advice.TrackLogging;
import com.module.usermodule.Dto.UserDto;
import com.module.usermodule.Service.UserService;
import com.module.usermodule.ServiceImpl.UserServiceImpl;

import io.swagger.annotations.ApiOperation;

/**
 * UserController is used for User end point.
 * @author Praba Singaravel
 *
 */
@RestController
@RequestMapping("/users")
public class UserController {

	@Lazy
	@Autowired
	public UserController(UserService userService,
			UserServiceImpl userServiceImpl) {
		this.userService = userService;
		this.userServiceImpl = userServiceImpl;
	}

	private final UserService userService;
	private final UserServiceImpl userServiceImpl;

	/**
	 * addUser method is used to register user.
	 * @param userDto
	 * @return UserDto
	 */
	@PostMapping(path="/",consumes= {"application/json"})
	@ApiOperation(value = "Insert User Detail", response = UserDto.class)
	@TrackExecutionTime
	@TrackLogging
	public UserDto addUser(@RequestBody UserDto userDto) {
		return userService.addUser(userDto);
	}

	/**
	 * getUserByName method is used to get specific user detail.
	 * @param userName
	 * @return UserDto
	 */
	@GetMapping(path="/{userName}",produces= {"application/json"})
	@Cacheable(value = "user", key = "#userName")
	@ApiOperation(value = "Fetch Specific User Detail", response = UserDto.class)
	@TrackExecutionTime
	@TrackLogging
	public UserDto getUserByName(@PathVariable String userName) {
		return userService.getUserByName(userName);
	}
	
	/**
	 * getAllUser method is used to get all user detail.
	 * @return List
	 */
	@GetMapping(path="/",produces= {"application/json"})
	@Cacheable(value = "user")
	@ApiOperation(value = "Fetch All User Detail", response = List.class)
	@TrackExecutionTime
	@TrackLogging
	public List<UserDto> getAllUser() {
		return userService.getAllUser();
	}
	
	/**
	 * deleteUser method is used to delete specific user detail.
	 * @param userId
	 * @return String
	 */
	@DeleteMapping(path="/{userId}")
	@CacheEvict(value = "user", key = "#userId")
	@ApiOperation(value = "Delete Specific User Detail", response = String.class)
	@TrackExecutionTime
	@TrackLogging
	public String deleteUser(@PathVariable long userId) {
		return userService.deleteUser(userId);
	}

	/**
	 * updateUser method is used to update user information.
	 * @param userDto
	 * @return UserDto
	 */
	@PutMapping(path="/", consumes= {"application/json"})
	@CachePut(value = "user", key="#userId")
	@ApiOperation(value = "Update User Detail", response = UserDto.class)
	@TrackExecutionTime
	@TrackLogging
	public UserDto updateUser(@RequestBody UserDto userDto) {
		return userService.updateUser(userDto);
	}
	
	/**
	 * generateToken method is used for token authentication.
	 * @param userDto
	 * @return String
	 * @throws Exception
	 */
	@PostMapping("/authenticate")
	@ApiOperation(value = "Insert authenticate Detail", response = String.class)
	public String generateToken(@RequestBody UserDto authRequest) throws Exception {
		return userServiceImpl.tokenInfo(authRequest.getUserName(), authRequest.getPassword());
	}
}
