package com.greggvandycke.Apollo.repositories;

import com.greggvandycke.Apollo.models.UserFavorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserFavoriteRepository extends JpaRepository<UserFavorite, Long> {

    Optional<UserFavorite> findFavoritesByUserId(long id);
}
