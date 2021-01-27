package com.module.usermodule.dto;

import java.io.Serializable;
import java.util.UUID;

import com.module.usermodule.entity.UserEntity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable{
	private static final long serialVersionUID = 4266140616476670021L;
	private UUID userId;
	private String userName;
	private String role;
	
	public static UserDto formDto(UserEntity userEntity) {
		UserDto userDto = new UserDto();
		userDto.setUserId(userEntity.getUserId());
		userDto.setUserName(userEntity.getUserName());
		userDto.setRole(userEntity.getRole());
		return userDto;
	}
	
	public static UserEntity formEntity(UserDto userDto) {
		UserEntity userEntity = new UserEntity();
		userEntity.setUserId(userDto.getUserId());
		userEntity.setUserName(userDto.getUserName());
		userEntity.setRole(userDto.getRole());
		return userEntity;
	}
}
