package com.etps.etps.repositories;

import com.etps.etps.models.Campus;
import com.etps.etps.models.Submission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Submissions extends JpaRepository<Submission, Long> {
    Submission findById(long id);


}
