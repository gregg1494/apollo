package com.greggvandycke.neutron;

import com.greggvandycke.neutron.config.AuditorAwareImpl;
import com.greggvandycke.neutron.models.*;
import com.greggvandycke.neutron.repositories.*;
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
public class NeutronApplication {

	public static void main(String[] args) {
		SpringApplication.run(NeutronApplication.class, args);
	}

	@Bean
	public AuditorAware<String> auditorAware() {
		return new AuditorAwareImpl();
	}

	@Bean
	public CommandLineRunner mapping(MovieRepository movieRepository, UserRepository userRepository, RoleRepository roleRepository, TheaterRepository theaterRepository, LocationRepository locationRepository) {

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

			Movie movie1 = new Movie("Coco", "1h 45min", "https://raw.githubusercontent.com/gvandycke/video-library/master/trailers/Coco.mp4", "https://raw.githubusercontent.com/gvandycke/video-library/master/pictures/Coco.jpg", false,  "22 November 2017", 5);
			Movie movie2 = new Movie("1917", "1h 59min", "https://raw.githubusercontent.com/gvandycke/video-library/master/trailers/1917.mp4", "https://raw.githubusercontent.com/gvandycke/video-library/master/pictures/1917.jpg", true, "10 January 2020", 5);
			Movie movie3 = new Movie("The Lord of the Rings: The Return of the King", "2h 58min", "https://raw.githubusercontent.com/gvandycke/video-library/master/trailers/TheLordoftheRingsTheReturnoftheKing.mp4", "https://raw.githubusercontent.com/gvandycke/video-library/master/pictures/TheLordoftheRingsTheReturnoftheKing.jpg", false, "19 July 2019", 5);
			Movie movie4 = new Movie("The Matrix", "2h 16min", "https://raw.githubusercontent.com/gvandycke/video-library/master/trailers/TheMatrix.mp4", "https://raw.githubusercontent.com/gvandycke/video-library/master/pictures/TheMatrix.jpg", false, "31 March 1999", 5);
			Movie movie5 = new Movie("The Dark Knight", "2h 32min", "https://raw.githubusercontent.com/gvandycke/video-library/master/trailers/TheDarkKnight.mp4", "https://raw.githubusercontent.com/gvandycke/video-library/master/pictures/TheDarkKnight.jpg", true, "18 July 2008", 5);
			Movie movie6 = new Movie("Interstellar", "2h 49min", "https://raw.githubusercontent.com/gvandycke/video-library/master/trailers/Interstellar.mp4", "https://raw.githubusercontent.com/gvandycke/video-library/master/pictures/Interstellar.jpg", true, " 7 November 2014", 5);
			Movie movie7 = new Movie("Saving Private Ryan", "2h 49min", "https://raw.githubusercontent.com/gvandycke/video-library/master/trailers/SavingPrivateRyan.mp4", "https://raw.githubusercontent.com/gvandycke/video-library/master/pictures/SavingPrivateRyan.jpg", false, "24 July 1998", 5);
			Movie movie8 = new Movie("Moana", "1h 47min", "https://raw.githubusercontent.com/gvandycke/video-library/master/trailers/Moana.mp4", "https://raw.githubusercontent.com/gvandycke/video-library/master/pictures/Moana.jpg", true, "23 November 2016", 5);
			Movie movie9 = new Movie("WALL·E", "1h 38min ", "https://raw.githubusercontent.com/gvandycke/video-library/master/trailers/WallE.mp4", "https://raw.githubusercontent.com/gvandycke/video-library/master/pictures/WallE.jpg", true, "27 June 2008", 5);
			Movie movie10 = new Movie("Frozen II", "1h 43min", "https://raw.githubusercontent.com/gvandycke/video-library/master/trailers/FrozenII.mp4", "https://raw.githubusercontent.com/gvandycke/video-library/master/pictures/FrozenII.jpg", false, "22 November 2019", 5);
			Movie movie11 = new Movie("The Lion King", "1h 58min", "https://raw.githubusercontent.com/gvandycke/video-library/master/trailers/TheLionKing.mp4", "https://raw.githubusercontent.com/gvandycke/video-library/master/pictures/TheLionKing.jpg", true, "19 July 2019", 5);

			movieRepository.saveAll(Arrays.asList(movie1, movie2, movie3, movie4, movie5, movie6, movie7, movie8, movie9, movie10, movie11));

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
