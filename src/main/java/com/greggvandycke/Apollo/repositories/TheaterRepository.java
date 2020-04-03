package com.greggvandycke.Apollo.repositories;

import com.greggvandycke.Apollo.models.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TheaterRepository extends JpaRepository<Theater, Long> {
}
