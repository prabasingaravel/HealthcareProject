package com.module.usermodule.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.module.usermodule.Model.Role;

/**
 * RoleRepository is a repository for role table.
 * @author Praba Singaravel
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	Role findByRoleId(int roleId);
}
