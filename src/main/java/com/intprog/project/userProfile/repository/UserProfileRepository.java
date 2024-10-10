package com.intprog.project.userProfile.repository;

import com.intprog.project.userProfile.model.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileRepository extends JpaRepository<UserProfile, Long> {
}
