package com.module.usermodule.ServiceImpl;

import java.util.List;
import java.util.Objects;
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
 * @since 21.02
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
	
	@Override
	public RoleDto addRole(RoleDto roleDto) {
		return RoleDto.convertRoleDto(roleRepository.save(RoleDto.convertRoleDomain(roleDto)));
	}

	@Override
	public List<RoleDto> getRoleDetails() {
		return roleRepository.findAll().stream().map(role -> RoleDto.convertRoleDto(role))
				.collect(Collectors.toList());
	}

	@Override
	public RoleDto getRoleById(int roleId) {
		return RoleDto.convertRoleDto(roleRepository.findByRoleId(roleId));
	}

	@Override
	public String deleteRole(int roleId) {
		Role role = roleRepository.findByRoleId(roleId);
		if(Objects.nonNull(role)) {
			roleRepository.delete(role);
			return "User Information is deleted with id " + roleId;
		}else {
			return "User Information not found for the id " + roleId;
		}
	}

	@Override
	public RoleDto updateRole(RoleDto roleDto) {
		return RoleDto.convertRoleDto(roleRepository.save(RoleDto.convertRoleDomain(roleDto)));
	}

}
