package com.greggvandycke.Apollo.resolvers.mutations;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.greggvandycke.Apollo.models.Movie;
import com.greggvandycke.Apollo.models.Theater;
import com.greggvandycke.Apollo.repositories.MovieRepository;
import com.greggvandycke.Apollo.repositories.TheaterRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MovieMutation implements GraphQLMutationResolver {

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
}
