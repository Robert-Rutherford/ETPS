package com.etps.etps.repositories;

import com.etps.etps.models.ProviderAddress;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderAddresses extends JpaRepository<ProviderAddress, Long> {
    ProviderAddress findById (long id);
}
