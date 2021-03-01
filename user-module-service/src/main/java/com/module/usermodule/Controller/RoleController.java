package com.module.usermodule.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.module.usermodule.Advice.TrackExecutionTime;
import com.module.usermodule.Advice.TrackLogging;
import com.module.usermodule.Dto.RoleDto;
import com.module.usermodule.Service.RoleService;

import io.swagger.annotations.ApiOperation;

/**
 * RoleController is used for role end point.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@RestController
@RequestMapping("/roles")
public class RoleController {
	
	private final RoleService roleService;
	
	@Lazy
	@Autowired
	public RoleController(RoleService roleService) {
		this.roleService = roleService;
	}

	/**
	 * addRole method is used to register Role.
	 * @param roleDto
	 * @return RoleDto
	 */
	@PostMapping(path="/",consumes= {"application/json"})
	@ApiOperation(value = "Insert Role Detail", response = RoleDto.class)
	@TrackExecutionTime
	@TrackLogging
	public RoleDto addRole(@RequestBody RoleDto roleDto) {
		return roleService.addRole(roleDto);
	}

	/**
	 * getRoleById method is used to fetch role detail based on role id.
	 * @param roleId
	 * @return RoleDto
	 */
	@GetMapping(path="/{roleId}",produces= {"application/json"})
	@Cacheable(value = "role", key = "#roleId")
	@ApiOperation(value = "Fetch Specific Role Detail", response = RoleDto.class)
	@TrackExecutionTime
	@TrackLogging
	public RoleDto getRoleById(@PathVariable int roleId) {
		return roleService.getRoleById(roleId);
	}
	
	/**
	 * getRoleDetails method is used to fetch all role detail.
	 * @return List of RoleDto
	 */
	@GetMapping(path="/",produces= {"application/json"})
	@Cacheable(value = "role")
	@ApiOperation(value = "Fetch All Role Detail", response = List.class)
	@TrackExecutionTime
	@TrackLogging
	public List<RoleDto> getRoleDetails() {
		return roleService.getRoleDetails();
	}
	
	/**
	 * deleteRole method is used to delete role detail based on role id.
	 * @param roleId
	 * @return String
	 */
	@DeleteMapping(path="/{roleId}")
	@CacheEvict(value = "role", key = "#roleId")
	@ApiOperation(value = "Delete Specific role Detail", response = String.class)
	@TrackExecutionTime
	@TrackLogging
	public String deleteRole(@PathVariable int roleId) {
		return roleService.deleteRole(roleId);
	}

	/**
	 * updateRole method is used to update role detail.
	 * @param roleDto
	 * @return RoleDto
	 */
	@PutMapping(path="/", consumes= {"application/json"})
	@CachePut(value = "role", key="#roleId")
	@ApiOperation(value = "Update Role Detail", response = RoleDto.class)
	@TrackExecutionTime
	@TrackLogging
	public RoleDto updateRole(@RequestBody RoleDto roleDto) {
		return roleService.updateRole(roleDto);
	}
}
