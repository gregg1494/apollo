package com.greggvandycke.Apollo.config;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;

public class AuditorAwareImpl implements AuditorAware<String> {

	@Override
	public Optional<String> getCurrentAuditor() {
		return Optional.of("Gregg");
	}
}

// ------------------ Use below code for spring security --------------------------

/*class SpringSecurityAuditorAware implements AuditorAware<User> {
	public User getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null || !authentication.isAuthenticated()) {
			return null;
		}
		return ((MyUserDetails) authentication.getPrincipal()).getUser();
	}
}*/