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

	/**
	 * findByUserName method is used to get user detail based on user name.
	 * @param userName
	 * @return User
	 */
	User findByUserName(String userName);

	/**
	 * findByUserId method is used to get user detail based on user id.
	 * @param userName
	 * @return User
	 */
	User findByUserId(Long userId);
}
