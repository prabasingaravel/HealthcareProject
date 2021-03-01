package com.module.usermodule.Dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import com.module.usermodule.Model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * RoleDto represent role table.
 * @author Praba Singaravel
 * @since 21.02
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
}
