package com.dev.scaffdone.core.scaffolding;

import com.dev.scaffdone.core.scaffolding.dto.ScaffoldingDTO;
import com.dev.scaffdone.core.scaffolding.model.Dimensions;
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
        Scaffolding newScaffolding = new Scaffolding();
        newScaffolding.fromDTO(scaffoldingDTO);
        scaffoldingDataRepository.save(newScaffolding);
        return scaffoldingDTO;
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
        Scaffolding owi = Scaffolding.builder().modules(List.of(new ScaffoldingModule(Dimensions.SIZE_073.getSize(), 5)))
                .done(true)
                .otherInformation("21JEDSAIODKJASKLDJSAKLDJASdasddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddKLDJKLSAJDSADJALSDK")
                .username("Lukasz").height(10.0f).build();

        Scaffolding owi2 = Scaffolding.builder().modules(List.of(new ScaffoldingModule(Dimensions.SIZE_073.getSize(), 5)))
                .done(true)
                .otherInformation("21JEDSAIODKJASKLDJSAKLDJASdasddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddKLDJKLSAJDSADJALSDK")
                .username("Tomek").height(10.0f).build();
        scaffoldingDataRepository.save(owi);
        scaffoldingDataRepository.save(owi2);
    }



}
