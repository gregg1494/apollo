package com.greggvandycke.Apollo.resolvers.queries;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.greggvandycke.Apollo.models.User;
import com.greggvandycke.Apollo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserQuery implements GraphQLQueryResolver {

	@Autowired
	private UserService userService;

	public List<User> users() {
		return userService.findAll();
	}

//	public User findByUsername(String username) {
//		return userService.findByUsername(username);
//	}
//
//	public Boolean existsByUsername(String username) {
//		return userService.existsByUsername(username);
//	}
}
