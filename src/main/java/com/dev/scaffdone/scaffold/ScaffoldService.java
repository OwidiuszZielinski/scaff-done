package com.dev.scaffdone.scaffold;

import com.dev.scaffdone.scaffold.entity.CalculateModule;
import com.dev.scaffdone.scaffold.entity.ScaffoldData;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ScaffoldService {


    private final ScaffoldDataRepository repository;


    public ScaffoldService(ScaffoldDataRepository repository) {
        this.repository = repository;
    }


    public ScaffoldData add(ScaffoldData scaffold) {
        float length = calculateLength(scaffold.getModules());

        return ScaffoldData.builder()
                .id(scaffold.getId())
                .height(scaffold.getHeight())
                .modules(scaffold.getModules())
                .username(scaffold.getUsername())
                .settled(scaffold.isSettled())
                .height(scaffold.getHeight())
                .frameSize(scaffold.getFrameSize())
                .totalLength(length)
                .resultSquareMeters(
                        calculateSquareMeters(
                                length,
                                scaffold.getHeight()
                        )

                )
                .build();

    }

    private float calculateSquareMeters(float totalLength, float height) {
        System.out.println(totalLength * height);
        return totalLength * height;
    }

    public static float calculateLength(List<CalculateModule> sizes) {
        return sizes.stream().map(e-> e.getSize()*e.getQuantity()).reduce(0f,Float::sum);

    }

    public void printData() {
        System.out.println(repository.findAll());

    }
}
