package com.greggvandycke.Apollo.resolvers.queries;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
		import com.greggvandycke.Apollo.models.Movie;
		import com.greggvandycke.Apollo.repositories.MovieRepository;
		import lombok.RequiredArgsConstructor;
		import org.springframework.stereotype.Component;

		import java.util.List;

@Component
@RequiredArgsConstructor
public class MovieQuery implements GraphQLQueryResolver {

	private final MovieRepository movieRepository;

	public List<Movie> movies() {
		return movieRepository.findAll();
	}
}
