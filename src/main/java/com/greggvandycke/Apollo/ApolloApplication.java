package com.greggvandycke.Apollo;

import com.greggvandycke.Apollo.config.AuditorAwareImpl;
import com.greggvandycke.Apollo.models.*;
import com.greggvandycke.Apollo.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Arrays;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditorAware")
public class ApolloApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApolloApplication.class, args);
	}

	@Bean
	public AuditorAware<String> auditorAware() {
		return new AuditorAwareImpl();
	}

	@Bean
	public CommandLineRunner mappingDemo(MovieRepository movieRepository, UserRepository userRepository, RoleRepository roleRepository, TheaterRepository theaterRepository, LocationRepository locationRepository) {

		return args -> {

			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String pass = encoder.encode("123");

			Role role1 = new Role(RoleName.ROLE_USER);
			Role role2 = new Role(RoleName.ROLE_ADMIN);

			roleRepository.saveAll(Arrays.asList(role1, role2));

			User user1 = new User("max", "jones", "max123", pass, "max@gmail.com", true);
			User user2 = new User("jim", "john", "jimmy12", pass, "jim@yahoo.com", true);

			user1.setRole(role1);
			user2.setRole(role2);

			userRepository.saveAll(Arrays.asList(user1, user2));

			Movie movie1 = new Movie("Coco", 123, "https://raw.githubusercontent.com/gregg1494/video-library/master/Coco.mp4");
			Movie movie2 = new Movie("1917", 135, "https://raw.githubusercontent.com/gregg1494/video-library/master/1917.mp4");
			Movie movie3 = new Movie("The Lion King", 144, "https://raw.githubusercontent.com/gregg1494/video-library/master/TheLionKing.mp4");

			movieRepository.saveAll(Arrays.asList(movie1, movie2, movie3));

			user1.getFavorites().addAll(Arrays.asList(movie1, movie2, movie3));
			user2.getFavorites().addAll(Arrays.asList(movie1, movie3));

			userRepository.saveAll(Arrays.asList(user1, user2));

			Location location1 = new Location("378 Lake St", "Antioch", 60002, "IL");

			locationRepository.saveAll(Arrays.asList(location1));

			Theater theater1 = new Theater("Antioch Movie Theater", location1);

			theaterRepository.saveAll(Arrays.asList(theater1));
		};
	}
}
