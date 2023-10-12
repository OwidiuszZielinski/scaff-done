package com.dev.scaffdone.core.scaffolding;

import com.dev.scaffdone.core.scaffolding.dto.ScaffoldingDTO;
import com.dev.scaffdone.core.scaffolding.model.Dimension;
import com.dev.scaffdone.core.scaffolding.model.Scaffolding;
import com.dev.scaffdone.core.scaffolding.model.ScaffoldingModule;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScaffoldingService {

    private final ScaffoldingDataRepository scaffoldingDataRepository;

    public ScaffoldingService(ScaffoldingDataRepository scaffoldingDataRepository) {
        this.scaffoldingDataRepository = scaffoldingDataRepository;
    }

    public ScaffoldingDTO add(ScaffoldingDTO scaffoldingDTO) {
        if(scaffoldingDTO.getModules().isEmpty()){
            throw new IllegalArgumentException("Scaffolding must have dimensions");
        }
        scaffoldingDTO.setDone(true);
        scaffoldingDTO.setTotalLength(calculateLength(scaffoldingDTO.getModules()));
        scaffoldingDTO.setResultSquareMeters(calculateSquareMeters(
                scaffoldingDTO.getTotalLength(),scaffoldingDTO.getHeight()
        ));
        Scaffolding newScaffolding = new Scaffolding();
        newScaffolding.fromDTO(scaffoldingDTO);
        scaffoldingDataRepository.save(newScaffolding);
        return scaffoldingDTO;
    }

    private float calculateSquareMeters(float totalLength, float height) {
        return totalLength * height;
    }

    public static float calculateLength(List<ScaffoldingModule> sizes) {
        return sizes.stream().map(e -> e.getDimension() * e.getQuantity()).reduce(0f, Float::sum);
    }

    public List<String> getUsedUsers(){
        return scaffoldingDataRepository.findAll().stream()
                .map(Scaffolding::getUsername)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Scaffolding> getScaffolds() {
        return scaffoldingDataRepository.findAll();
    }

    public void initTestData() {
        System.out.println("@@@@@@@@@@@wywolana");
        Scaffolding owi = Scaffolding.builder().modules(List.of(new ScaffoldingModule(Dimension.SIZE_073.getSize(), 5)))
                .done(true)
                .otherInformation("21JEDSAIODKJASKLDJSAKLDJASdasddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddKLDJKLSAJDSADJALSDK")
                .username("TWOJA STARA").height(10.0f).build();

        Scaffolding owi2 = Scaffolding.builder().modules(List.of(new ScaffoldingModule(Dimension.SIZE_073.getSize(), 5)))
                .done(true)
                .otherInformation("21JEDSAIODKJASKLDJSAKLDJASdasddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddKLDJKLSAJDSADJALSDK")
                .username("Powidiusz").height(10.0f).build();
        scaffoldingDataRepository.save(owi);
        scaffoldingDataRepository.save(owi2);
    }

}
