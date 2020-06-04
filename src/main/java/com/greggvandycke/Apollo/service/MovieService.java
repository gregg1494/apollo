package com.greggvandycke.Apollo.service;

import com.greggvandycke.Apollo.models.Movie;
import com.greggvandycke.Apollo.repositories.MovieRepository;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@Slf4j
@GraphQLApi
@Service
@AllArgsConstructor
@CrossOrigin(origins="http://localhost:3000")
public class MovieService {

	private final MovieRepository movieRepository;

	@GraphQLMutation
	public Movie createMovie(String title, int length) {
		Movie movie = new Movie();
		movie.setTitle(title);
		movie.setLength(length);
		movieRepository.save(movie);
		return movie;
	}

	@GraphQLQuery
	public List<Movie> movies(){
		return movieRepository.findAll();
	}

	@GraphQLMutation
	public boolean deleteMovie(long id) {
		movieRepository.deleteById(id);
		return true;
	}

	@GraphQLMutation
	public Movie updateMovie(long id, String title, int length) throws NotFoundException {
		Optional<Movie> optionalMovie = movieRepository.findById(id);

		if (optionalMovie.isPresent()) {
			Movie movie = optionalMovie.get();

			if (title != null) {
				movie.setTitle(title);
			}
			if(length > 0) {
				movie.setLength(length);
			}

			movieRepository.save(movie);
			return movie;
		}

		throw new NotFoundException("No found movie to update!");
	}

	@GraphQLQuery
	public Movie getMovie(long id) {
		Optional<Movie> optionalMovie = movieRepository.findById(id);
		return optionalMovie.orElse(null);
	}

	@GraphQLQuery
	public Movie findByTitle(String title) {
		Optional<Movie> optionalMovie = movieRepository.findByTitle(title);
		return optionalMovie.orElse(null);
	}

	@GraphQLQuery
	public Boolean existsByTitle(String title) {
		return movieRepository.existsByTitle(title);
	}
}
