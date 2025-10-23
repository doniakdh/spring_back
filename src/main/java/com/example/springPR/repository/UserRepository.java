package com.example.springPR.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springPR.Model.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
    User findByUsername(String username);
}
