package com.example.demo.entity;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Singers")
public class Singer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fullname;
    private String description;
    private String image;

    @JsonIgnore
    @OneToMany(mappedBy = "singer", cascade = CascadeType.ALL)
    private List<SingerAlbum> singerAlbums;

    @JsonIgnore
    @OneToMany(mappedBy = "singer", cascade = CascadeType.ALL)
    private List<SongSinger> songSinger;
    
    @JsonIgnore
    @OneToMany(mappedBy = "singer", cascade = CascadeType.ALL)
    private List<FavoriteSinger> favoriteSinger;
    
}
