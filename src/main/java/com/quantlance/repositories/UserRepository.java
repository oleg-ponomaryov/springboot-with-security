package com.quantlance.repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quantlance.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Collection<User> findAllByEmail(String email);
}
