package com.module.usermodule.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.module.usermodule.Model.User;

/**
 * UserRepository is a repository for user table.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	User findByUserName(String userName);

	User findByUserId(Long userId);
}
