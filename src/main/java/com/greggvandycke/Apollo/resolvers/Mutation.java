package com.greggvandycke.Apollo.resolvers;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.greggvandycke.Apollo.models.Movie;
import com.greggvandycke.Apollo.repositories.MovieRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class Mutation implements GraphQLMutationResolver {

	private final MovieRepository movieRepository;

	public Movie createMovie(String title, int length) {
		Movie movie = new Movie();
		movie.setTitle(title);
		movie.setLength(length);
		movieRepository.save(movie);
		return movie;
	}

	public boolean deleteMovie(long id) {
		movieRepository.deleteById(id);
		return true;
	}

	public Movie updateMovie(long id, String title, int length) throws NotFoundException {
		Optional<Movie> optMovie = movieRepository.findById(id);

		if (optMovie.isPresent()) {
			Movie movie = optMovie.get();

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
}
