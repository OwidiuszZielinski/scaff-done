package com.dev.scaffdone.core.scaffolding.model;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Scaffolding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "JSON")
    private List<ScaffoldingModule> modules;
    private boolean done;
    private float height;
    private Dimensions frameDim;
    private String username;
    private float totalLength;
    private float resultSquareMeters;
    private String otherInformation;

    public void fromDTO(com.dev.scaffdone.core.scaffolding.dto.ScaffoldingDTO scaffoldingDTO){
        this.modules = scaffoldingDTO.getModules();
        this.done = scaffoldingDTO.isDone();
        this.height = scaffoldingDTO.getHeight();
        this.frameDim = scaffoldingDTO.getFrameDim();
        this.username = scaffoldingDTO.getUsername();
        this.totalLength = scaffoldingDTO.getTotalLength();
        this.resultSquareMeters = scaffoldingDTO.getResultSquareMeters();

        this.otherInformation = scaffoldingDTO.getOtherInformation();

    }
}

