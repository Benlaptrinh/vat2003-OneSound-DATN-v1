package com.project.shopapp.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Accounts")
@ToString(exclude = { "accountRole", "passwordResetTokens" })
public class Account implements UserDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String password;
	private String fullname;
	@Column(unique = true)
	private String email;
	private boolean active;
	@Temporal(TemporalType.DATE)
	private Date createdDate = new Date();
	private String Phone;
	private String address;
	private String avatar_url;
	private boolean gender;
	@Temporal(TemporalType.DATE)
	private Date birthday;
	private String phonenumber;

	@Enumerated(EnumType.STRING)
	private AuthProvider provider;

	@ManyToOne
	@JoinColumn(name = "role_id")
	private Role accountRole;

	@OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
	private List<PasswordResetToken> passwordResetTokens;

	@Column(name = "facebook_account_id")
	private int facebookAccountId;

	@Column(name = "google_account_id")
	private int googleAccountId;

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

	// @Override
	// public Collection<? extends GrantedAuthority> getAuthorities() {
	// List<SimpleGrantedAuthority> roles = new ArrayList<>();
	// roles.add(new SimpleGrantedAuthority("ROLE_" +
	// getAccountRole().getName().toUpperCase()));
	// return roles;
	// }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> roles = new ArrayList<>();

		// Kiểm tra nếu accountRole không phải là null trước khi thêm vào danh sách
		if (getAccountRole() != null) {
			roles.add(new SimpleGrantedAuthority("ROLE_" +
					getAccountRole().getName().toUpperCase()));
		}

		return roles;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
