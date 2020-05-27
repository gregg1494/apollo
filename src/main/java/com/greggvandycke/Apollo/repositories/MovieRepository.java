package com.greggvandycke.Apollo.repositories;

import com.greggvandycke.Apollo.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

	Optional<Movie> findByTitle(String title);

	boolean existsByTitle(String title);
}
