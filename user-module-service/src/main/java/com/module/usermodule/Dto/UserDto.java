package com.module.usermodule.Dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * UserDto represent User table.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable{
	private static final long serialVersionUID = 4266140616476670021L;
	private long userId;
	private String userName;
	private String password;
	private int roleId;
	private String createdBy;
	private LocalDateTime createdAt;
	private String updatedBy;
	private LocalDateTime updatedAt;
}
