package com.example.groom.domain.file;

import com.example.groom.entity.Attachment;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<Attachment, Long> {
    Optional<Attachment> findByUuidFileName(String filename);
}
