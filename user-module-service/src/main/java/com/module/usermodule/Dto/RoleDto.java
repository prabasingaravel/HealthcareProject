package com.module.usermodule.Dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.module.usermodule.Model.Role;
import com.module.usermodule.Model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RoleDto represent role table.
 * @author Praba Singaravel
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoleDto implements Serializable {
	private static final long serialVersionUID = 7240824051008293128L;
	private int roleId;
	private String roleName;
	private List<User> roles;
	private String createdBy;
	private LocalDateTime createdAt;
	private String updatedBy;
	private LocalDateTime updatedAt;
	
	public static Role convertRoleDomain(RoleDto roleDto) {
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

	public static RoleDto convertRoleDto(Role role) {
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
