package com.intprog.project.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.intprog.project.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findOneByLoginIgnoreCase(String login);
}
