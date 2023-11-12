package com.dev.scaffdone.core.scaffolding;

import com.dev.scaffdone.core.scaffolding.model.Scaffolding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ScaffoldingDataRepository extends JpaRepository<Scaffolding,Long> {
    @Query("select s from Scaffolding s where s.username = ?1")
    List<Scaffolding> findByUsername(String username);

    Optional<Scaffolding> findById(Long id);

}
