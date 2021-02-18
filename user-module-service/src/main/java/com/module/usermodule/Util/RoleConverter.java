package com.module.usermodule.Util;

import com.module.usermodule.Dto.RoleDto;
import com.module.usermodule.Model.Role;

/**
 * RoleConverter is used to convert entity to dto and dto to entity.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
public class RoleConverter {
	public static Role convertToRoleEntity(RoleDto roleDto) {
		if (roleDto == null) {
			return null;
		}
		Role role = new Role();
		role.setRoleId(roleDto.getRoleId());
		role.setRoleName(roleDto.getRoleName());
		role.setRoles(roleDto.getRoles());
		role.setCreatedAt(roleDto.getCreatedAt());
		role.setCreatedBy(roleDto.getCreatedBy());
		role.setUpdatedAt(roleDto.getUpdatedAt());
		role.setUpdatedBy(roleDto.getUpdatedBy());
		return role;
	}

	public static RoleDto convertToRoleDto(Role role) {
		if (role == null) {
			return null;
		}
		RoleDto roleDto = new RoleDto();
		roleDto.setRoleId(role.getRoleId());
		roleDto.setRoleName(role.getRoleName());
		roleDto.setRoles(role.getRoles());
		roleDto.setCreatedAt(role.getCreatedAt());
		roleDto.setCreatedBy(role.getCreatedBy());
		roleDto.setUpdatedAt(role.getUpdatedAt());
		roleDto.setUpdatedBy(role.getUpdatedBy());
		return roleDto;
	}
}
