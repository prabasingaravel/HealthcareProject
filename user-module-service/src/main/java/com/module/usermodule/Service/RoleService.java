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

	public RoleDto addRole(RoleDto roleDto);

	public List<RoleDto> getRoleDetails();
	
	public RoleDto getRoleById(int roleId);
	
	public String deleteRole(int roleId);
	
	public RoleDto updateRole(RoleDto roleDto);
}
