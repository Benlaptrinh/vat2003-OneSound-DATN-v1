package com.project.shopapp.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "Youtubes")
public class Youtube {

    @Id
    private String id;
    private String title;
    private String description;
    private String thumbnails;
    private String channelTitle;
    private String publishTime;

}
