package com.module.usermodule.entity;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="User_Information")
public class UserEntity implements Serializable{
	private static final long serialVersionUID = -341894181636256969L;
	@Id
	@Column(name="user_id")
	@GeneratedValue(generator = "uuid")
	private UUID userId;
	@Column(name="user_name")
	@ApiModelProperty(notes = "Name of the user", name = "userName", required = true)
	private String userName;
	@Column(name="password")
	@ApiModelProperty(notes = "Password of the user", name = "password", required = true)
	private String password;
	@Column(name="role")
	@ApiModelProperty(notes = "Role of the user", name = "role", required = true)
	private String role;
}
