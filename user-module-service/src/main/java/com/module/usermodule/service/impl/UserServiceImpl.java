package com.module.usermodule.service.impl;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.module.usermodule.dto.UserDto;
import com.module.usermodule.entity.UserEntity;
import com.module.usermodule.repository.UserRepository;
import com.module.usermodule.service.UserService;

@Service
public class UserServiceImpl  implements UserDetailsService, UserService{
	
	@Autowired
	public UserServiceImpl(UserRepository userRepo) {
		super();
		this.userRepo=userRepo;
	}

	private UserRepository userRepo;

	public UserDto addUser(UserDto userDto) {
		return UserDto.formDto(userRepo.save(UserDto.formEntity(userDto)));
	}

	public UserDto getUserInfo(String userName) {
		UserDto user = UserDto.formDto(userRepo.findByUserName(userName));
		if(Objects.nonNull(user)) {
			return user;
		}else {
			throw new RuntimeException("User not found for the name "+ userName);
		}
	}

	public String getAllUserInfo() {
		if(userRepo.findAll() != null) {
			return userRepo.findAll().toString();
		} else {
			return "User Information not available in data base";
		}
	}
	
	public String DeleteUserInfo(int userId) {
		if(userRepo.findById(userId) != null) {
			userRepo.delete(userRepo.findByUserId(userId));
			return "User Information is deleted with id " + userId;
		}else {
			return "User Information not found for the id " + userId;
		}
	}
	
	public UserDto updateUserDetail(UserDto user) {
		return UserDto.formDto(userRepo.save(UserDto.formEntity(user)));
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity userEntity = userRepo.findByUserName(username);
		return new User(userEntity.getUserName(),userEntity.getPassword(),new ArrayList<>());
	}
}
