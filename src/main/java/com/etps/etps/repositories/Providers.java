package com.etps.etps.repositories;

import com.etps.etps.models.Campus;
import com.etps.etps.models.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Providers extends JpaRepository<Provider, Long> {
    Provider findById(long id);
    Provider findAllById(long id);
    Provider findAllByProvId(long id);
    Provider existsById(long id);
}
