package com.rrohan419.bookreview.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rrohan419.bookreview.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
    
    User findByNameAndPassword(String name, String password);
}
