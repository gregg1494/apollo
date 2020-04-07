package com.greggvandycke.Apollo.repositories;

import com.greggvandycke.Apollo.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {

//	Optional<Movie> getMovie(long id);
}
