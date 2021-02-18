package com.module.usermodule.Service;

import java.util.List;

import com.module.usermodule.Dto.UserDto;

/**
 * UserService is used for user table CRUD operation.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
public interface UserService {

	/**
	 * addUser method is used to register user.
	 * @param userDto
	 * @return UserDto
	 */
	public UserDto addUser(UserDto userDto);

	/**
	 * getAllUser method is used to get all user detail.
	 * @return List of UserDto
	 */
	public List<UserDto> getAllUser();
	
	/**
	 * getUserByName method is used to get user detail based on user name.
	 * @param userName
	 * @return UserDto
	 */
	public UserDto getUserByName(String username);
	
	/**
	 * deleteUser method is used to delete user detail based on user id.
	 * @param userId
	 * @return String
	 */
	public String deleteUser(long userId);
	
	/**
	 * updateUser method is used to update user information.
	 * @param userDto
	 * @return UserDto
	 */
	public UserDto updateUser(UserDto userDto);
	
	/**
	 * generateToken method is used for token authentication.
	 * @param userDto
	 * @return String
	 * @throws Exception
	 */
	public String tokenInfo(String username, String password) throws Exception;
}
