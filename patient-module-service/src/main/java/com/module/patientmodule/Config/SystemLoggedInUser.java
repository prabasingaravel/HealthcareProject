package com.module.patientmodule.Config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

/**
 * SystemLoggedInUser is used to update login user.
 * @author Praba Singaravel
 *
 */
@Component
public class SystemLoggedInUser implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.of(System.getProperty("user.name"));
	}

}
