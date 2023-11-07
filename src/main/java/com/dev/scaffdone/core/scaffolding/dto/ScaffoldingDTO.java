package com.dev.scaffdone.core.scaffolding.dto;

import com.dev.scaffdone.core.scaffolding.model.Dimensions;
import com.dev.scaffdone.core.scaffolding.model.Scaffolding;
import com.dev.scaffdone.core.scaffolding.model.ScaffoldingModule;
import com.dev.scaffdone.core.user.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ScaffoldingDTO {

    private Long id;
    private List<ScaffoldingModule> modules;
    private boolean done;
    private float height;
    private Dimensions frameDim;
    private String username;
    private float totalLength;
    private float resultSquareMeters;
    private User user;
    private String otherInformation;

    public static ScaffoldingDTO from(Scaffolding scaffolding){
        return com.dev.scaffdone.core.scaffolding.dto.ScaffoldingDTO.builder()
                .id(scaffolding.getId())
                .modules(scaffolding.getModules())
                .done(scaffolding.isDone())
                .height(scaffolding.getHeight())
                .frameDim(scaffolding.getFrameDim())
                .username(scaffolding.getUsername())
                .totalLength(scaffolding.getTotalLength())
                .resultSquareMeters(scaffolding.getResultSquareMeters())
                .user(scaffolding.getUser())
                .otherInformation(scaffolding.getOtherInformation())
                .build();
    }
}
