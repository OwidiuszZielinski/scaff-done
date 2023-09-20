package com.dev.scaffdone.scaffold;

import com.dev.scaffdone.scaffold.entity.Scaffold;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ScaffoldDataRepository extends JpaRepository<Scaffold,Long> {


}
