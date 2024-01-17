package com.example.demo.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Accounts")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String password;
	private String fullname;
	private String email;
	private boolean active;
	@Temporal(TemporalType.DATE)
	private Date createdDate = new Date();

	private String address;
	private String avatar_url;
	private boolean gender;

	@ManyToOne
	@JoinColumn(name = "role_id") // Đảm bảo tên cột khớp với tên cột trong cơ sở dữ liệu
	private Role accountRole;

	@OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
	private PasswordResetToken passwordResetToken;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	@JsonManagedReference
	List<FavoriteAlbum> favoriteAlbums;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	@JsonManagedReference
	List<FavoriteSong> favoriteSongs;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	List<FavoriteSinger> favoriteSingers;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	List<FavoriteGenre> favoriteGenres;

	@JsonIgnore
	@OneToMany(mappedBy = "user")
	List<FollowUser> followUsers;

	@JsonIgnore
	@OneToMany(mappedBy = "following")
	List<FollowUser> following;
}
