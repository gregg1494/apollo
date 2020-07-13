package com.greggvandycke.neutron.repositories;

import com.greggvandycke.neutron.models.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {

}
