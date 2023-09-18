package com.dev.scaffdone.scaffold;

import com.dev.scaffdone.scaffold.entity.ScaffoldData;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ScaffoldDataRepository extends CrudRepository<ScaffoldData,Long> {


}
