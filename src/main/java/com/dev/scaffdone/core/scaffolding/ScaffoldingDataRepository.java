package com.dev.scaffdone.core.scaffolding;

import com.dev.scaffdone.core.scaffolding.model.Scaffolding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ScaffoldingDataRepository extends JpaRepository<Scaffolding, Long> {
    Optional<Scaffolding> findById(Long id);
}
