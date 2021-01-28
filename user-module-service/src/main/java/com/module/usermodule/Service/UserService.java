package com.module.usermodule.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.module.usermodule.Dto.UserDto;
import com.module.usermodule.Manager.UserManager;
import com.module.usermodule.Model.User;
import com.module.usermodule.Util.JwtUtil;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	public UserService(UserManager userManager, JwtUtil jwtUtil) {
		super();
		this.userManager = userManager;
		this.jwtUtil = jwtUtil;
	}

	private final UserManager userManager;
	private final JwtUtil jwtUtil;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userManager.getUserByName(username);
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				new ArrayList<>());

	}

	public String tokenInfo(String username, String password) {
		User user = userManager.getUserByName(username);
		String token = jwtUtil.generateToken(username);
		return "Welcome to "+ user.getRole() + " Dash board Mr/Ms : " + username + " Your Token is " + token;

	}
	
	public User addUser(UserDto userDto) {
		return userManager.addUser(userDto);
	}

	public List<User> getAllUserInfo() {
		return userManager.getAllUser();
	}
	
	public User getUserInfo(String username) {
		return userManager.getUserByName(username);
	}
	
	public String DeleteUserInfo(long userId) {
		return userManager.deleteUser(userId);
	}
	
	public User updateUserDetail(UserDto userDto) {
		return userManager.updateUser(userDto);
	}
}
