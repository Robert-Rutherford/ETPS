package com.etps.etps.repositories;

import com.etps.etps.models.ProviderAdditionalContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderAdditionalContacts extends JpaRepository<ProviderAdditionalContact, Long> {
    ProviderAdditionalContact findById (long id);
}
