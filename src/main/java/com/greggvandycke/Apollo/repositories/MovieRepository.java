package com.greggvandycke.Apollo.repositories;

import com.greggvandycke.Apollo.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
