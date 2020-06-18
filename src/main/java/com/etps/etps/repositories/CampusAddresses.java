package com.etps.etps.repositories;

import com.etps.etps.models.CampusAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampusAddresses extends JpaRepository<CampusAddress, Long> {
    CampusAddress findById (long id);
}
