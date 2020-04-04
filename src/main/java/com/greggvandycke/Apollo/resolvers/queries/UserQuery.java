package com.greggvandycke.Apollo.resolvers.queries;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.greggvandycke.Apollo.models.User;
import com.greggvandycke.Apollo.repositories.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserQuery implements GraphQLQueryResolver {
	private final UserRepository userRepository;

	public List<User> users() {
		return userRepository.findAll();
	}
}
