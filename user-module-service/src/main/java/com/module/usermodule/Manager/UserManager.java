package com.module.usermodule.Manager;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.module.usermodule.Dto.UserDto;
import com.module.usermodule.Model.User;
import com.module.usermodule.Repository.UserRepository;

@Service
public class UserManager {

	@Autowired
	public UserManager(UserRepository userRepositary) {
		super();
		this.userRepositary = userRepositary;
	}

	private final UserRepository userRepositary;

	public User addUser(UserDto userDto) {
		User user = new User();
		user.setUserId(userDto.getUserId());
		user.setUserName(userDto.getUserName());
		user.setPassword(userDto.getPassword());
		user.setRole(userDto.getRole());
		return userRepositary.save(user);

	}

	public User getUserByName(String username) {
		return userRepositary.findByUserName(username);
	}

	public List<User> getAllUser() {
		return userRepositary.findAll();
	}
	
	public String deleteUser(long userId) {
		if(userRepositary.findByUserId(userId) != null) {
			userRepositary.delete(userRepositary.findByUserId(userId));
			return "User Information is deleted with id " + userId;
		}else {
			return "User Information not found for the id " + userId;
		}
	}
	
	public User updateUser(UserDto userDto) {
		User user = new User();
		user.setUserId(userDto.getUserId());
		user.setUserName(userDto.getUserName());
		user.setPassword(userDto.getPassword());
		user.setRole(userDto.getRole());
		return userRepositary.save(user);
	}

}
