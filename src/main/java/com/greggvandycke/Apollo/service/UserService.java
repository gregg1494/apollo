package com.greggvandycke.Apollo.service;

import com.greggvandycke.Apollo.exception.InvalidCredentialsException;
import com.greggvandycke.Apollo.models.User;
import com.greggvandycke.Apollo.repositories.UserRepository;
import com.greggvandycke.Apollo.security.jwt.JwtTokenUtil;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@GraphQLApi
@Service
@AllArgsConstructor
public class UserService {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	private final UserRepository userRepository;

	@GraphQLMutation
	@Transactional
	public User createUser(String name, String username, String password) {
		User user = new User();
		user.setName(name);
		user.setUsername(username);
		user.setPassword(password);
		userRepository.save(user);
		return user;
	}

	@GraphQLQuery
	@Transactional(readOnly = true)
	public List<User> users() {
		return userRepository.findAll();
	}

	@GraphQLMutation
	@Transactional
	public boolean deleteUser(long id) {
		userRepository.deleteById(id);
		return true;
	}

	@GraphQLMutation
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

	@GraphQLQuery
	@Transactional
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@GraphQLQuery
	@Transactional
	public User findByUsername(String username) {
		Optional<User> optionalUser = userRepository.findByUsername(username);
		return optionalUser.orElse(null);
	}

	@GraphQLQuery
	@Transactional
	public Boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	@GraphQLQuery
	public String signin(String username, String password) throws InvalidCredentialsException {
		Optional<User> user = userRepository.findByUsername(username);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if(user.isPresent()) {
			if (encoder.matches(password, user.get().getPassword())) {
				log.info("success...");
				return jwtTokenUtil.generateToken(user.get().getUsername());
			} else {
				log.info("Invalid Credentials1");
				throw new InvalidCredentialsException("Invalid Credentials!");
			}
		} else{
			log.info("Invalid Credentials2");
			throw new InvalidCredentialsException("Invalid Credentials!");
		}
	}

}
