package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "followUsers")
public class FollowUser {

	@Id
	@ManyToOne
	@JoinColumn(name = "account_id")
	private Account user;

	@Id
	@ManyToOne
	@JoinColumn(name = "following_id")
	private Account following;

}
