package com.project.shopapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SingerDTO {
    private Long id;
    private String fullname;
    private String description;
    private String image;

}
