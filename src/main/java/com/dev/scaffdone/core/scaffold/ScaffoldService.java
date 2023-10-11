package com.dev.scaffdone.core.scaffold;

import com.dev.scaffdone.core.scaffold.ScaffoldDataRepository;
import com.dev.scaffdone.core.scaffold.model.ScaffoldModule;
import com.dev.scaffdone.core.scaffold.model.Scaffold;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScaffoldService {

    private final ScaffoldDataRepository repository;

    public ScaffoldService(ScaffoldDataRepository repository) {
        this.repository = repository;
    }

    public Scaffold add(Scaffold scaffold) {
        float length = calculateLength(scaffold.getModules());
        return Scaffold.builder()
                .id(scaffold.getId())
                .height(scaffold.getHeight())
                .modules(scaffold.getModules())
                .username(scaffold.getUsername())
                .done(scaffold.isDone())
                .height(scaffold.getHeight())
                .frameDim(scaffold.getFrameDim())
                .totalLength(length)
                .otherInformation(scaffold.getOtherInformation())
                .resultSquareMeters(
                        calculateSquareMeters(
                                length,
                                scaffold.getHeight()
                        )

                )
                .build();
    }

    private float calculateSquareMeters(float totalLength, float height) {
        return totalLength * height;
    }

    public static float calculateLength(List<ScaffoldModule> sizes) {
        return sizes.stream().map(e -> e.getDimension() * e.getQuantity()).reduce(0f, Float::sum);
    }


}
