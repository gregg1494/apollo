package com.greggvandycke.Apollo.resolvers.mutations;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.greggvandycke.Apollo.models.Movie;
import com.greggvandycke.Apollo.service.MovieService;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MovieMutation implements GraphQLMutationResolver {

	@Autowired
	private MovieService movieService;

	public Movie createMovie(String title, int length) {
		return movieService.createMovie(title, length);
	}

	public boolean deleteMovie(long id) {
		movieService.deleteById(id);
		return true;
	}

	public Movie updateMovie(long id, String title, int length) throws NotFoundException {
		return movieService.updateMovie(id, title, length);
	}
}
