package com.dev.scaffdone.scaffold.entity;


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
public class Scaffold {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "JSON")
    private List<CalculateModule> modules;
    private boolean done;
    private float height;
    private Size frameSize;
    private String username;
    private float totalLength;
    private float resultSquareMeters;
    @ManyToOne
    private UserDetails userDetails;
    private String additionalInfo;


}

