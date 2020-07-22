package com.greggvandycke.neutron.service;

import com.greggvandycke.neutron.exception.InvalidCredentialsException;
import com.greggvandycke.neutron.exception.RegistrationException;
import com.greggvandycke.neutron.models.*;
import com.greggvandycke.neutron.repositories.MovieRepository;
import com.greggvandycke.neutron.repositories.RoleRepository;
import com.greggvandycke.neutron.repositories.UserRepository;
import com.greggvandycke.neutron.security.jwt.JwtTokenUtil;
import com.greggvandycke.neutron.security.jwt.JwtUserDetailsService;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@Slf4j
@GraphQLApi
@Service
@AllArgsConstructor
@CrossOrigin(origins="http://localhost:3000")
public class UserService {

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;

	@Autowired
	private final UserRepository userRepository;
	@Autowired
	private final MovieRepository movieRepository;
	@Autowired
	private final RoleRepository roleRepository;

	@GraphQLMutation
	public User createUser(String name, String username, String password) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		userRepository.save(user);
		return user;
	}

	//@PreAuthorize("hasRole('ADMIN')")
	@GraphQLQuery
	public List<User> users() {
		return userRepository.findAll();
	}

	@GraphQLQuery
	public User user(long id) {
		return userRepository.findById(id).orElse(null);
	}

	@GraphQLMutation
	public boolean deleteUser(long id) {
		userRepository.deleteById(id);
		return true;
	}

	@GraphQLMutation
	public User updateUser(long id, String name, String username, String password) throws NotFoundException {
		Optional<User> optionalUser = userRepository.findById(id);

		if (optionalUser.isPresent()) {
			User user = optionalUser.get();

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
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@GraphQLQuery
	public User findByUsername(String username) {
		Optional<User> optionalUser = userRepository.findByUsername(username);
		return optionalUser.orElse(null);
	}

	@GraphQLQuery
	public Boolean existsByUsername(String username) {
		return userRepository.existsByUsername(username);
	}

	@GraphQLQuery
	public List<Movie> getFavorites(@GraphQLContext User u) {
		Optional<User> optionalUser = userRepository.findById(u.getId());
		return optionalUser.map(User::getFavorites).orElse(null);
	}

	@GraphQLMutation
	public Movie addFavorite(long userId, long movieId) {
		Movie movie = movieRepository.findById(movieId).orElse(null);
		User user = userRepository.getOne(userId);
		user.getFavorites().add(movie);
		userRepository.save(user);
		return movie;
	}

	@GraphQLMutation
	public boolean removeFavorite(long userId, long movieId) {
		Movie movie = movieRepository.findById(movieId).orElse(null);
		User user = userRepository.getOne(userId);
		user.getFavorites().remove(movie);
		userRepository.save(user);
		return true;
	}

	@GraphQLMutation
	public User register(String firstName, String lastName, String email, String username, String password, String confirmPassword) throws RegistrationException {

		if(firstName== null || lastName == null || username == null || email == null || password == null || confirmPassword == null) {
			throw new RegistrationException("User field is null");
		}

		if(!password.equals(confirmPassword)) {
			throw new RegistrationException("Passwords do not match");
		}

		Role role = new Role();
		role.setRoleName(RoleName.ROLE_USER);
		roleRepository.save(role);

		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(password);
		User user = new User(firstName, lastName, username, encodedPassword, email, true);
		userRepository.save(user);
		return user;
	}

	@GraphQLMutation
	public User login(String username, String password) throws InvalidCredentialsException {
		log.info("username {}", username);
		Optional<User> user = userRepository.findByUsername(username);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if(user.isPresent()) {
			if(encoder.matches(password, user.get().getPassword())) {
				log.info("login success...");
				jwtTokenUtil.generateToken(user.get().getUsername());
				return user.get();
			} else {
				log.info("Invalid Credentials");
				throw new InvalidCredentialsException("Invalid Credentials");
			}
		} else {
			log.info("Invalid Credentials2");
			throw new InvalidCredentialsException("Invalid Credentials");
		}
	}

	@GraphQLMutation
	public boolean logout(String username) {
		log.info("user is logging out");
		jwtTokenUtil.invalidateToken(username);
		return true;
	}

	@GraphQLQuery
	public User verifyToken(String token) {
		Optional<User> optionalUser = userRepository.findByToken(token);
		if(optionalUser.isPresent()) {
			UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(optionalUser.get().getUsername());
			if(jwtTokenUtil.isTokenValid(token, userDetails)) {
				return optionalUser.get();
			}
		}
		return null;
	}
}