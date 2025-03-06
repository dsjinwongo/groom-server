package com.example.groom.domain.auth.user;

import com.example.groom.entity.domain.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
