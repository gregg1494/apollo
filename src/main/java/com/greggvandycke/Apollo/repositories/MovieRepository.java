package com.greggvandycke.Apollo.repositories;

import com.greggvandycke.Apollo.models.Movie;
import org.springframework.data.repository.CrudRepository;

public interface MovieRepository extends CrudRepository<Movie, Long> {
}
