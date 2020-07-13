package com.greggvandycke.neutron.repositories;

import com.greggvandycke.neutron.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

	Optional<Movie> findByTitle(String title);

	boolean existsByTitle(String title);
}
