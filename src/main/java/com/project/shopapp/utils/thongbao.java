package com.project.shopapp.utils;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class thongbao {
    @JsonProperty("message")
    private String message;
}
