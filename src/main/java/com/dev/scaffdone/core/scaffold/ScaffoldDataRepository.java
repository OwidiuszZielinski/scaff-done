package com.dev.scaffdone.core.scaffold;

import com.dev.scaffdone.core.scaffold.model.Scaffold;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ScaffoldDataRepository extends JpaRepository<Scaffold,Long> {


}
