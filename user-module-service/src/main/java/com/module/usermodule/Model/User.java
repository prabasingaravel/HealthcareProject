package com.module.usermodule.Model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="User")
@EntityListeners(AuditingEntityListener.class)
public class User implements Serializable{
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
	@CreatedBy
	@Column(name = "created_by")
	@ApiModelProperty(notes = "user who created the entity", name = "createdBy", required = true)
	private String createdBy;
	@CreatedDate
	@Column(name = "created_at")
	private LocalDateTime createdAt;
	@LastModifiedBy
	@Column(name = "updated_by")
	@ApiModelProperty(notes = "user who modified the entity", name = "updatedBy", required = true)
	private String updatedBy;
	@LastModifiedDate
	@Column(name = "updated_at")
	private LocalDateTime updateAt;
}
