package com.dev.scaffdone.scaffold;

import com.dev.scaffdone.scaffold.entity.ScaffoldData;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ScaffoldController {

    private final ScaffoldDataRepository repository;

    public ScaffoldController(ScaffoldDataRepository repository) {
        this.repository = repository;
    }

//    List<ScaffoldData> findAllPosts() {
//        return repository.findAll();
//    }


}
