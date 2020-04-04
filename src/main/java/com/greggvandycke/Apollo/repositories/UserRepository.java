package com.greggvandycke.Apollo.repositories;

import com.greggvandycke.Apollo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
