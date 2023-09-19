package com.dev.scaffdone.scaffold.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ScaffoldData {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;
    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition="JSON")
    private List<CalculateModule> modules;
    private boolean settled;
    private float height;
    private Size frameSize;
    private String username;
    private float totalLength;
    private float resultSquareMeters;


}


// public record ScaffoldData(
//        @Id
//        Long id,
//        Size size, boolean settled, float height, Size frameSize, String username) {


//}
