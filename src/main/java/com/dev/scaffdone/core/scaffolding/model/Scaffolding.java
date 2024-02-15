package com.dev.scaffdone.core.scaffolding.model;

import com.dev.scaffdone.core.scaffolding.dto.ScaffoldingDTO;
import com.dev.scaffdone.core.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private LocalDateTime date;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "JSON")
    private List<ScaffoldingModule> modules;
    private boolean done;
    private float height;
    private String username;
    private float totalLength;
    private float resultSquareMeters;
    @ManyToOne
    private User user;
    private String otherInformation;

    public void fromDTO(ScaffoldingDTO scaffoldingDTO) {
        this.date = LocalDateTime.parse(scaffoldingDTO.getDate());
        this.modules = scaffoldingDTO.getModules();
        this.done = scaffoldingDTO.isDone();
        this.height = scaffoldingDTO.getHeight();
        this.username = scaffoldingDTO.getUsername();
        this.totalLength = scaffoldingDTO.getTotalLength();
        this.resultSquareMeters = scaffoldingDTO.getResultSquareMeters();
        this.user = scaffoldingDTO.getUser();
        this.otherInformation = scaffoldingDTO.getOtherInformation();
    }

    public String getDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        return date.format(formatter);
    }
}

