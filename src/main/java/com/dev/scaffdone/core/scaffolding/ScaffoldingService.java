package com.dev.scaffdone.core.scaffolding;

import com.dev.scaffdone.core.scaffolding.dto.ScaffoldingDTO;
import com.dev.scaffdone.core.scaffolding.model.Dimensions;
import com.dev.scaffdone.core.scaffolding.model.Scaffolding;
import com.dev.scaffdone.core.scaffolding.model.ScaffoldingModule;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
        scaffoldingDTO.setDate(String.valueOf(LocalDateTime.now()));
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

    public Optional<Scaffolding> getScaffold(Long id){
        return scaffoldingDataRepository.findById(id);
    }

    public void updateDone(Scaffolding scaffolding){
        scaffoldingDataRepository.saveAndFlush(scaffolding);
    }

    public void initTestData() {

        Scaffolding owi = Scaffolding.builder()
                .date(LocalDateTime.now())
                .modules(List.of(new ScaffoldingModule(Dimensions.SIZE_073.getSize(), 5),
                        new ScaffoldingModule(Dimensions.SIZE_073.getSize(), 5),
                        new ScaffoldingModule(Dimensions.SIZE_207.getSize(), 2),
                        new ScaffoldingModule(Dimensions.SIZE_414.getSize(), 1),
                        new ScaffoldingModule(Dimensions.SIZE_307.getSize(), 9)))

                .done(true)
                .otherInformation("21JEDSAIODKJASKLDJSAKLDJASdasddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddKLDJKLSAJDSADJALSDK")
                .username("David").height(10.0f).build();

        Scaffolding owi2 = Scaffolding.builder()
                .date(LocalDateTime.now())
                .modules(List.of(new ScaffoldingModule(Dimensions.SIZE_207.getSize(), 1),
                        new ScaffoldingModule(Dimensions.SIZE_073.getSize(), 5),
                        new ScaffoldingModule(Dimensions.SIZE_207.getSize(), 2),
                        new ScaffoldingModule(Dimensions.SIZE_414.getSize(), 1),
                        new ScaffoldingModule(Dimensions.SIZE_307.getSize(), 9)))
                .done(true)
                .otherInformation("21JEDSAIODKJASKLDJSAKLDJASdasddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddKLDJKLSAJDSADJALSDK")
                .username("John").height(10.0f).build();
        Scaffolding owi3 = Scaffolding.builder()
                .date(LocalDateTime.now())
                .modules(List.of(new ScaffoldingModule(Dimensions.SIZE_109.getSize(), 2),
                        new ScaffoldingModule(Dimensions.SIZE_073.getSize(), 5),
                        new ScaffoldingModule(Dimensions.SIZE_207.getSize(), 2),
                        new ScaffoldingModule(Dimensions.SIZE_414.getSize(), 1),
                        new ScaffoldingModule(Dimensions.SIZE_307.getSize(), 9)))
                .done(true)
                .otherInformation("21JEDSAIODKJASKLDJSAKLDJASdasddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddKLDJKLSAJDSADJALSDK")
                .username("David").height(10.0f).build();

        Scaffolding owi4 = Scaffolding.builder()
                .date(LocalDateTime.now())
                .modules(List.of(new ScaffoldingModule(Dimensions.SIZE_257.getSize(), 3),
                        new ScaffoldingModule(Dimensions.SIZE_073.getSize(), 5),
                        new ScaffoldingModule(Dimensions.SIZE_207.getSize(), 2),
                        new ScaffoldingModule(Dimensions.SIZE_414.getSize(), 1),
                        new ScaffoldingModule(Dimensions.SIZE_307.getSize(), 9)))
                .done(true)
                .otherInformation("21JEDSAIODKJASKLDJSAKLDJASdasddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddKLDJKLSAJDSADJALSDK")
                .username("John").height(10.0f).build();
        Scaffolding owi5 = Scaffolding.builder()
                .date(LocalDateTime.now())
                .modules(List.of(new ScaffoldingModule(Dimensions.SIZE_400.getSize(), 1),
                        new ScaffoldingModule(Dimensions.SIZE_073.getSize(), 5),
                        new ScaffoldingModule(Dimensions.SIZE_207.getSize(), 2),
                        new ScaffoldingModule(Dimensions.SIZE_414.getSize(), 1),
                        new ScaffoldingModule(Dimensions.SIZE_307.getSize(), 9)))
                .done(true)
                .otherInformation("21JEDSAIODKJASKLDJSAKLDJASdasddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddKLDJKLSAJDSADJALSDK")
                .username("David").height(10.0f).build();

        Scaffolding owi6 = Scaffolding.builder()
                .date(LocalDateTime.now())
                .modules(List.of(new ScaffoldingModule(Dimensions.SIZE_150.getSize(), 3),
                        new ScaffoldingModule(Dimensions.SIZE_073.getSize(), 5),
                        new ScaffoldingModule(Dimensions.SIZE_207.getSize(), 2),
                        new ScaffoldingModule(Dimensions.SIZE_414.getSize(), 1),
                        new ScaffoldingModule(Dimensions.SIZE_307.getSize(), 9)))
                .done(true)
                .otherInformation("21JEDSAIODKJASKLDJSAKLDJASdasddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddKLDJKLSAJDSADJALSDK")
                .username("John").height(10.0f).build();
        scaffoldingDataRepository.save(owi);
        scaffoldingDataRepository.save(owi2);
        scaffoldingDataRepository.save(owi3);
        scaffoldingDataRepository.save(owi4);
        scaffoldingDataRepository.save(owi5);
        scaffoldingDataRepository.save(owi6);

    }



}
