package com.etps.etps.repositories;

import com.etps.etps.models.CampusAdditionalContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampusAdditionalContacts extends JpaRepository<CampusAdditionalContact, Long> {
    CampusAdditionalContact findById (long id);
}
