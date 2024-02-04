package com.project.shopapp.utils;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UpdateUserDTO {
    @JsonProperty("fullname")
    private String fullName;

    @JsonProperty("email")
    private String email;

    @JsonProperty("password")
    private String password;

    @JsonProperty("address")
    private String address;

    @JsonProperty("avatar_url")
    private String avatar_url;

    @JsonProperty("gender")
    private boolean gender;

    @Temporal(TemporalType.DATE)
    private Date createdDate = new Date();

}
