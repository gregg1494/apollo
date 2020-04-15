package com.greggvandycke.Apollo.service;

import com.greggvandycke.Apollo.models.Movie;
import com.greggvandycke.Apollo.repositories.MovieRepository;
import io.leangen.graphql.annotations.GraphQLMutation;
import io.leangen.graphql.annotations.GraphQLQuery;
import io.leangen.graphql.spqr.spring.annotations.GraphQLApi;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@GraphQLApi
@Service
@AllArgsConstructor
public class MovieService {

	private MovieRepository movieRepository;

	@GraphQLMutation
	@Transactional
	public Movie createMovie(String title, int length) {
		Movie movie = new Movie();
		movie.setTitle(title);
		movie.setLength(length);
		movieRepository.save(movie);
		return movie;
	}

	@GraphQLQuery
	@Transactional(readOnly = true)
	public List<Movie> movies(){
		return movieRepository.findAll();
	}

	@GraphQLQuery
	@Transactional
	public boolean deleteMovie(long id) {
		movieRepository.deleteById(id);
		return true;
	}

	@GraphQLMutation
	@Transactional
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
	@Transactional(readOnly = true)
	public Movie getMovie(long id) {
		Optional<Movie> optionalMovie = movieRepository.findById(id);
		return optionalMovie.orElse(null);
	}

	@GraphQLQuery
	@Transactional(readOnly = true)
	public Movie findByTitle(String title) {
		Optional<Movie> optionalMovie = movieRepository.findByTitle(title);
		return optionalMovie.orElse(null);
	}

	@GraphQLQuery
	@Transactional(readOnly = true)
	public Boolean existsByTitle(String title) {
		return movieRepository.existsByTitle(title);
	}
}
