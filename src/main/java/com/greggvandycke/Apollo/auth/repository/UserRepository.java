package com.greggvandycke.Apollo.auth.repository;

import com.greggvandycke.Apollo.auth.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
