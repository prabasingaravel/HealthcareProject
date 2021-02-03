package com.module.usermodule.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.module.usermodule.Dto.RoleDto;
import com.module.usermodule.Model.Role;
import com.module.usermodule.Repository.RoleRepository;
import com.module.usermodule.Service.RoleService;

/**
 * RoleServiceImpl which implements RoleService.
 * @author Praba Singaravel
 *
 */
@Service
public class RoleServiceImpl implements RoleService{

	@Lazy
	@Autowired
	public RoleServiceImpl(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	private final RoleRepository roleRepository;
	
	public RoleDto addRole(RoleDto roleDto) {
		return RoleDto.convertRoleDto(roleRepository.save(RoleDto.convertRoleDomain(roleDto)));
	}

	public List<RoleDto> getRoleDetails() {
		return roleRepository.findAll().stream().map(a -> RoleDto.convertRoleDto(a))
				.collect(Collectors.toList());
	}

	public RoleDto getRoleById(int roleId) {
		return RoleDto.convertRoleDto(roleRepository.findByRoleId(roleId));
	}

	public String deleteRole(int roleId) {
		Role role = roleRepository.findByRoleId(roleId);
		if(role != null) {
			roleRepository.delete(role);
			return "User Information is deleted with id " + roleId;
		}else {
			return "User Information not found for the id " + roleId;
		}
	}

	public RoleDto updateRole(RoleDto roleDto) {
		return RoleDto.convertRoleDto(roleRepository.save(RoleDto.convertRoleDomain(roleDto)));
	}

}
