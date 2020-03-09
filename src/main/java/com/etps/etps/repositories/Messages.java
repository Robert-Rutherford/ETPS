package com.etps.etps.repositories;

import com.etps.etps.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Messages extends JpaRepository<Message, Long> {
    Message findById(long id);


}
