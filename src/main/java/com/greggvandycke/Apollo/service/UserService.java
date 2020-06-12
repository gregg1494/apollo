package com.greggvandycke.Apollo.service;

import com.greggvandycke.Apollo.exception.InvalidCredentialsException;
import com.greggvandycke.Apollo.models.Movie;
import com.greggvandycke.Apollo.models.User;
import com.greggvandycke.Apollo.repositories.MovieRepository;
import com.greggvandycke.Apollo.repositories.UserRepository;
import com.greggvandycke.Apollo.security.jwt.JwtTokenUtil;
import io.leangen.graphql.annotations.GraphQLContext;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@GraphQLApi
@Service
@AllArgsConstructor
public class UserService {

	private JwtTokenUtil jwtTokenUtil;

	private final UserRepository userRepository;
	private final MovieRepository movieRepository;

	@GraphQLMutation
	public User createUser(String name, String username, String password) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		userRepository.save(user);
		return user;
	}

	@PreAuthorize("hasRole('ADMIN')")
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
	public String login(String username, String password) throws InvalidCredentialsException {
		Optional<User> user = userRepository.findByUsername(username);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if(user.isPresent()) {
			if(encoder.matches(password, user.get().getPassword())) {
				log.info("success...");
				return jwtTokenUtil.generateToken(user.get().getUsername());
			} else {
				log.info("Invalid Credentials1");
				throw new InvalidCredentialsException("Invalid Credentials!");
			}
		} else {
			log.info("Invalid Credentials2");
			throw new InvalidCredentialsException("Invalid Credentials!");
		}
	}

	@GraphQLMutation
	public boolean logout(String username) {
		jwtTokenUtil.invalidateToken(username);
		return true;
	}
}