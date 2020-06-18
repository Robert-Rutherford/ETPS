package com.etps.etps.repositories;

import com.etps.etps.models.CampusContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampusContacts extends JpaRepository<CampusContact, Long> {
    CampusContact findById (long id);
}
