package com.greggvandycke.Apollo;

import com.greggvandycke.Apollo.models.Movie;
import com.greggvandycke.Apollo.models.User;
import com.greggvandycke.Apollo.repositories.MovieRepository;
import com.greggvandycke.Apollo.repositories.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
public class ApolloApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApolloApplication.class, args);
	}

	@Bean
	public CommandLineRunner mappingDemo(MovieRepository movieRepository, UserRepository userRepository) {

		return args -> {

			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String pass = encoder.encode("123");

			User user1 = new User("max", "jones", "max123", pass, "max@gmail.com");
			User user2 = new User("jim", "john", "jimmy12", pass, "jim@yahoo.com");

			userRepository.save(user1);
			userRepository.save(user2);

			Movie movie1 = new Movie("Coco", 123, "https://media.w3.org/2010/05/sintel/trailer_hd.mp4");
			Movie movie2 = new Movie("1917", 135, "https://media.w3.org/2010/05/sintel/trailer_hd.mp4");
			Movie movie3 = new Movie("Matrix", 144, "https://media.w3.org/2010/05/sintel/trailer_hd.mp4");

			movieRepository.saveAll(Arrays.asList(movie1, movie2, movie3));

			user1.getFavorites().addAll(Arrays.asList(movie1, movie2, movie3));
			user2.getFavorites().addAll(Arrays.asList(movie1, movie3));

			userRepository.save(user1);
			userRepository.save(user2);
		};
	}
}
