package com.module.usermodule.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.module.usermodule.Model.Role;

/**
 * RoleRepository is a repository for role table.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{
	Role findByRoleId(int roleId);
}
