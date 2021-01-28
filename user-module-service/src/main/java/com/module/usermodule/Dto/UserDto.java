package com.module.usermodule.Dto;

import java.io.Serializable;
import java.util.UUID;

import com.module.usermodule.Model.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable{
	private static final long serialVersionUID = 4266140616476670021L;
	private UUID userId;
	private String userName;
	private String password;
	private String role;
}
