package com.module.usermodule.Service;

import java.util.List;

import com.module.usermodule.Dto.UserDto;

/**
 * UserService is used for user table CRUD operation.
 * @author Praba Singaravel
 *
 */
public interface UserService {

	public UserDto addUser(UserDto userDto);

	public List<UserDto> getAllUser();
	
	public UserDto getUserByName(String username);
	
	public String deleteUser(long userId);
	
	public UserDto updateUser(UserDto userDto);
}
