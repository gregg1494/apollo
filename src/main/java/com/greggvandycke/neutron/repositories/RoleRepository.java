package com.greggvandycke.neutron.repositories;

import com.greggvandycke.neutron.models.Role;
import com.greggvandycke.neutron.models.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByRoleName(RoleName roleName);
}
