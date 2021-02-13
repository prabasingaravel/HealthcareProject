package com.module.usermodule.Config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * SystemLoggedInUser is used to update login user.
 * @author Praba Singaravel
 * @since 21.02
 *
 */
@Component
public class SystemLoggedInUser implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		String name = "SYSTEM";
		if(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {
			name = SecurityContextHolder.getContext().getAuthentication().getName();
		}
		return Optional.ofNullable(name);
	}

}
