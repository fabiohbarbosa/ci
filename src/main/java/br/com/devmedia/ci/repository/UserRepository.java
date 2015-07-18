package br.com.devmedia.ci.repository;

import br.com.devmedia.ci.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
