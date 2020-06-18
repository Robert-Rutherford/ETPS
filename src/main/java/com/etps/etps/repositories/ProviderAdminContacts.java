package com.etps.etps.repositories;

import com.etps.etps.models.ProviderAdminContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderAdminContacts extends JpaRepository<ProviderAdminContact, Long> {
    ProviderAdminContact findById (long id);
}
