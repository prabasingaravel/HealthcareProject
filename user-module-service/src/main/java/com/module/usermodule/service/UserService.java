package com.module.usermodule.service;

import com.module.usermodule.dto.UserDto;

public interface UserService {

	public UserDto addUser(UserDto userDto);

	public UserDto getUserInfo(String userName);

	public String getAllUserInfo();
	
	public String DeleteUserInfo(int userId);
	
	public UserDto updateUserDetail(UserDto user);
}
