package com.greggvandycke.Apollo.service;

import com.greggvandycke.Apollo.models.Movie;
import com.greggvandycke.Apollo.repositories.MovieRepository;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MovieService {

	private MovieRepository movieRepository;

	@Transactional
	public Movie createMovie(String title, int length) {
		Movie movie = new Movie();
		movie.setTitle(title);
		movie.setLength(length);
		movieRepository.save(movie);
		return movie;
	}

	@Transactional(readOnly = true)
	public List<Movie> movies(){
		return movieRepository.findAll();
	}

	@Transactional
	public boolean deleteById(long id) {
		movieRepository.deleteById(id);
		return true;
	}

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
}
