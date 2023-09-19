package com.dev.scaffdone.scaffold;

import com.dev.scaffdone.scaffold.entity.ScaffoldData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScaffoldService {


    private final ScaffoldDataRepository repository;


    public ScaffoldService(ScaffoldDataRepository repository) {
        this.repository = repository;
    }


    public ScaffoldData add(ScaffoldData scaffold) {

        return ScaffoldData.builder()
                .id(scaffold.getId())
                .height(scaffold.getHeight())
                .sizes(scaffold.getSizes())
                .username(scaffold.getUsername())
                .settled(scaffold.isSettled())
                .height(scaffold.getHeight())
                .frameSize(scaffold.getFrameSize())
                .totalLength(calculateLength(scaffold.getSizes()))
                .resultSquareMeters(
                        calculateSquareMeters(
                                calculateLength(scaffold.getSizes()),
                                scaffold.getHeight()
                        )

                )
                .build();

    }

    private float calculateSquareMeters(float totalLength, float height) {
        System.out.println(totalLength * height);
        return totalLength * height;
    }

    private float calculateLength(List<Float> sizes) {
        return sizes
                .stream()
                .reduce(0f, Float::sum);

    }

    public void printData() {
        System.out.println(repository.findAll());

    }
}
