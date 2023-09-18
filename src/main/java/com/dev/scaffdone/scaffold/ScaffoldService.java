package com.dev.scaffdone.scaffold;

import org.springframework.stereotype.Service;

@Service
public class ScaffoldService {


    private final ScaffoldDataRepository repository;


    public ScaffoldService(ScaffoldDataRepository repository) {
        this.repository = repository;
    }


    public void printData(){
        System.out.println(repository.findAll());

    }
}
