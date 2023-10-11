package com.dev.scaffdone.core.scaffolding;

import com.dev.scaffdone.core.scaffolding.model.Scaffolding;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ScaffoldingDataRepository extends JpaRepository<Scaffolding,Long> {


}
