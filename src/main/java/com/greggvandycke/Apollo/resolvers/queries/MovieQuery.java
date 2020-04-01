package com.greggvandycke.Apollo.resolvers.queries;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.greggvandycke.Apollo.models.Movie;
import com.greggvandycke.Apollo.models.Theater;
import com.greggvandycke.Apollo.repositories.MovieRepository;
import com.greggvandycke.Apollo.repositories.TheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MovieQuery implements GraphQLQueryResolver {

	private final MovieRepository movieRepository;

	public Iterable<Movie> movies() {
		return movieRepository.findAll();
	}
}
