package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.composite.FollowUserId;
import com.example.demo.entity.FollowUser;

public interface FollowDAO extends JpaRepository<FollowUser, FollowUserId>{

}
