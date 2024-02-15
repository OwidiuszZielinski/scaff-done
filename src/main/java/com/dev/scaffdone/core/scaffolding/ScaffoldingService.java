package com.dev.scaffdone.core.scaffolding;

import com.dev.scaffdone.core.scaffolding.dto.ScaffoldingDTO;
import com.dev.scaffdone.core.scaffolding.model.Scaffolding;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScaffoldingService {

    private final ScaffoldingDataRepository scaffoldingDataRepository;

    public ScaffoldingDTO add(ScaffoldingDTO scaffoldingDTO) {
        if (scaffoldingDTO.getModules().isEmpty()) {
            throw new IllegalArgumentException("Scaffolding must have dimensions");
        }
        scaffoldingDTO.setDone(true);
        scaffoldingDTO.setDate(String.valueOf(LocalDateTime.now()));
        Scaffolding newScaffolding = new Scaffolding();
        newScaffolding.fromDTO(scaffoldingDTO);
        scaffoldingDataRepository.save(newScaffolding);
        return scaffoldingDTO;
    }

    public List<String> getUsedUsers() {
        return scaffoldingDataRepository.findAll().stream()
                .map(Scaffolding::getUsername)
                .distinct()
                .collect(Collectors.toList());
    }

    public List<Scaffolding> getScaffolds() {
        return scaffoldingDataRepository.findAll();
    }

    public Optional<Scaffolding> getScaffold(Long id) {
        return scaffoldingDataRepository.findById(id);
    }

    public void updateDone(Scaffolding scaffolding) {
        scaffoldingDataRepository.saveAndFlush(scaffolding);
    }
}
