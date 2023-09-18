package com.dev.scaffdone.scaffold.entity;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import java.time.LocalDate;


//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Entity
//public class ScaffoldData {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private Size size;
//    private boolean settled;
//    private float height;
//    private Size frameSize;
//    private String username;
//
//
//}


 public record ScaffoldData(
        @Id
        Long id,
        Size size, boolean settled, float height, Size frameSize, String username) {


}
