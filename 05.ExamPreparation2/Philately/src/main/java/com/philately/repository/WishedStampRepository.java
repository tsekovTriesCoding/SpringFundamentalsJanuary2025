package com.philately.repository;

import com.philately.model.entity.WishedStamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WishedStampRepository extends JpaRepository<WishedStamp, UUID> {
}
