package com.module.usermodule.Service;

import java.util.List;

import com.module.usermodule.Dto.RoleDto;

/**
 * RoleService is used for role table CRUD operation.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
public interface RoleService {

	/**
	 * addRole method is used to register Role.
	 * @param roleDto
	 * @return RoleDto
	 */
	public RoleDto addRole(RoleDto roleDto);

	/**
	 * getRoleDetails method is used to fetch all role detail.
	 * @return List of RoleDto
	 */
	public List<RoleDto> getRoleDetails();
	
	/**
	 * getRoleById method is used to fetch role detail based on role id.
	 * @param roleId
	 * @return RoleDto
	 */
	public RoleDto getRoleById(int roleId);
	
	/**
	 * deleteRole method is used to delete role detail based on role id.
	 * @param roleId
	 * @return String
	 */
	public String deleteRole(int roleId);
	
	/**
	 * updateRole method is used to update role detail.
	 * @param roleDto
	 * @return RoleDto
	 */
	public RoleDto updateRole(RoleDto roleDto);
}
