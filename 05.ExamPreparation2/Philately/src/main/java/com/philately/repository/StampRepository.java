package com.philately.repository;

import com.philately.model.entity.Stamp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface StampRepository extends JpaRepository<Stamp, UUID> {
    List<Stamp> findAllByOwnerIdNot(UUID userId);
}
