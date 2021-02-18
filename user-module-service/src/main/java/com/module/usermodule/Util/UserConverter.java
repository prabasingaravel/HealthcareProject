package com.module.usermodule.Util;

import com.module.usermodule.Dto.UserDto;
import com.module.usermodule.Model.User;

/**
 * UserConverter is used to convert entity to dto and dto to entity.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
public class UserConverter {
	public static User convertToUserEntity(UserDto userDto) {
		if (userDto == null) {
			return null;
		}
		User user = new User();
		user.setUserId(userDto.getUserId());
		user.setUserName(userDto.getUserName());
		user.setPassword(userDto.getPassword());
		user.setRoleId(userDto.getRoleId());
		user.setCreatedAt(userDto.getCreatedAt());
		user.setCreatedBy(userDto.getCreatedBy());
		user.setUpdatedAt(userDto.getUpdatedAt());
		user.setUpdatedBy(userDto.getUpdatedBy());
		return user;
	}

	public static UserDto convertToUserDto(User user) {
		if (user == null) {
			return null;
		}
		UserDto userDto = new UserDto();
		userDto.setUserId(user.getUserId());
		userDto.setUserName(user.getUserName());
		userDto.setPassword(user.getPassword());
		userDto.setRoleId(user.getRoleId());
		userDto.setCreatedAt(user.getCreatedAt());
		userDto.setCreatedBy(user.getCreatedBy());
		userDto.setUpdatedAt(user.getUpdatedAt());
		userDto.setUpdatedBy(user.getUpdatedBy());
		return userDto;
	}
}
