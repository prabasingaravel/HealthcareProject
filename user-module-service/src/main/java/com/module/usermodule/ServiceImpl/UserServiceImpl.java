package com.module.usermodule.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.module.usermodule.Dto.UserDto;
import com.module.usermodule.ExceptionHandling.ResourceNotFoundException;
import com.module.usermodule.Model.Role;
import com.module.usermodule.Model.User;
import com.module.usermodule.Repository.RoleRepository;
import com.module.usermodule.Repository.UserRepository;
import com.module.usermodule.Service.UserService;
import com.module.usermodule.Util.JwtUtil;
import com.module.usermodule.Util.UserConverter;

/**
 * UserServiceImpl which implements UserService and UserDetailsService.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final JwtUtil jwtUtil;
	private final AuthenticationManager authenticationManager;
	
	@Lazy
	@Autowired
	public UserServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager,
			JwtUtil jwtUtil, RoleRepository roleRepository) {
		this.userRepository = userRepository;
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
		this.roleRepository = roleRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserDto user = UserConverter.convertToUserDto(userRepository.findByUserName(username));
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				new ArrayList<>());
	}

	@Override
	public String tokenInfo(String username, String password) throws Exception {
		try {

			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));

		} catch (Exception e) {
			throw new Exception("invaild Username/Password");
		}
		User user = userRepository.findByUserName(username);
		Role role = roleRepository.findByRoleId(user.getRoleId());
		String token = jwtUtil.generateToken(username);
		return "Welcome to "+ role.getRoleName() + " Dash board Mr/Ms : " + username + " Your Token is " + token;
	}
	
	@Override
	public UserDto addUser(UserDto userDto) {
		return UserConverter.convertToUserDto(userRepository.save(UserConverter.convertToUserEntity(userDto)));
	}

	@Override
	public List<UserDto> getAllUser() {
		return userRepository.findAll().stream().map(user -> UserConverter.convertToUserDto(user))
				.collect(Collectors.toList());
	}
	
	@Override
	public String deleteUser(long userId) {
		User user = userRepository.findByUserId(userId);
		if(Objects.nonNull(user)) {
			userRepository.delete(user);
			return "User Information is deleted with id " + userId;
		}else {
			throw new ResourceNotFoundException("User Information not found for the id " + userId);
		}
	}
	
	@Override
	public UserDto updateUser(UserDto userDto) {
		User user = userRepository.findByUserId(userDto.getUserId());
		if(Objects.nonNull(user)) {
			return UserConverter.convertToUserDto(userRepository.save(UserConverter.convertToUserEntity(userDto)));
		}else {
			throw new ResourceNotFoundException("User Information not found for the name " + userDto.getUserName());
		}
	}

	@Override
	public UserDto getUserById(long userId) {
		return UserConverter.convertToUserDto(userRepository.findByUserId(userId));
	}
}
