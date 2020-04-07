package com.greggvandycke.Apollo.service;

import com.greggvandycke.Apollo.models.User;
import com.greggvandycke.Apollo.repositories.UserRepository;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

	private final UserRepository userRepository;

	@Transactional
	public User createUser(String name, String username, String password) {
		User user = new User();
		user.setName(name);
		user.setUsername(username);
		user.setPassword(password);
		userRepository.save(user);
		return user;
	}

	@Transactional(readOnly = true)
	public List<User> users() {
		return userRepository.findAll();
	}

	@Transactional
	public boolean deleteById(long id) {
		userRepository.deleteById(id);
		return true;
	}

	@Transactional
	public User updateUser(long id, String name, String username, String password) throws NotFoundException {
		Optional<User> optionalUser = userRepository.findById(id);

		if (optionalUser.isPresent()) {
			User user = optionalUser.get();

			if (name != null) {
				user.setName(name);
			}
			if (username != null) {
				user.setUsername(username);
			}
			if (password != null) {
				user.setPassword(password);
			}

			userRepository.save(user);
			return user;
		}

		throw new NotFoundException("No found user to update!");
	}

	@Transactional
	public List<User> findAll() {
		return userRepository.findAll();
	}

//	@Transactional
//	public User findByUsername(String username) {
//		Optional<User> optionalUser = userRepository.findByUsername(username);
//		return optionalUser.orElse(null);
//	}
//
//	@Transactional
//	public Boolean existsByUsername(String username) {
//		return userRepository.existsByUsername(username);
//	}
}
