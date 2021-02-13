package com.module.usermodule.Dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.module.usermodule.Model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserDto represent User table.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable{
	private static final long serialVersionUID = 4266140616476670021L;
	private long userId;
	private String userName;
	private String password;
	private int roleId;
	private String createdBy;
	private LocalDateTime createdAt;
	private String updatedBy;
	private LocalDateTime updatedAt;
	
	public static User convertUserDomain(UserDto userDto) {
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

	public static UserDto convertUserDto(User user) {
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
