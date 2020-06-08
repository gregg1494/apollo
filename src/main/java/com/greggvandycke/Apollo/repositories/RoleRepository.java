package com.greggvandycke.Apollo.repositories;

import com.greggvandycke.Apollo.models.Role;
import com.greggvandycke.Apollo.models.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleName(RoleName roleName);
}
