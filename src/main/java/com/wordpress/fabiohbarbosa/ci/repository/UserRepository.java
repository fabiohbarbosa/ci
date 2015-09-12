package com.wordpress.fabiohbarbosa.ci.repository;

import com.wordpress.fabiohbarbosa.ci.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
