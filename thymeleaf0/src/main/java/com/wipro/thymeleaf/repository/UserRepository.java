package com.wipro.thymeleaf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.wipro.thymeleaf.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
