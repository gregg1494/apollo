package com.greggvandycke.Apollo.resolvers;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.greggvandycke.Apollo.models.Movie;
import com.greggvandycke.Apollo.repositories.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Query implements GraphQLQueryResolver {

	private final MovieRepository movieRepository;

	public Iterable<Movie> movies() {
		return movieRepository.findAll();
	}
}
