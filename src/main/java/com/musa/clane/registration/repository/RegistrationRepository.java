package com.musa.clane.registration.repository;

import com.musa.clane.registration.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RegistrationRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
}
