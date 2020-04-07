package com.greggvandycke.Apollo.resolvers.queries;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.greggvandycke.Apollo.models.Movie;
import com.greggvandycke.Apollo.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieQuery implements GraphQLQueryResolver {

	@Autowired
	private MovieService movieService;

	public List<Movie> movies() {
		return movieService.movies();
	}

	public Movie getMovie(long id) {
		return movieService.getMovie(id);
	}
}
