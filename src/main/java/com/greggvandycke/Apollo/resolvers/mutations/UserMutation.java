package com.greggvandycke.Apollo.resolvers.mutations;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.greggvandycke.Apollo.models.User;
import com.greggvandycke.Apollo.service.UserService;
import javassist.NotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMutation implements GraphQLMutationResolver {

	@Autowired
	private UserService userService;

	public User createUser(String name, String username, String password) {
		return userService.createUser(name, username, password);
	}

	public boolean deleteUser(long id) {
		userService.deleteById(id);
		return true;
	}

	public User updateUser(long id, String name, String username, String password) throws NotFoundException {
		return userService.updateUser(id, name, username, password);
	}
}
