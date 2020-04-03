package com.greggvandycke.Apollo.resolvers.queries;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.greggvandycke.Apollo.auth.model.User;
import com.greggvandycke.Apollo.auth.repository.UserRepository;
import com.greggvandycke.Apollo.models.Movie;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserQuery implements GraphQLQueryResolver {
	private final UserRepository userRepository;

	public List<User> movies() {
		return userRepository.findAll();
	}
}
