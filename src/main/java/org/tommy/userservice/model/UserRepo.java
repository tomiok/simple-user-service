package org.tommy.userservice.model;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {

  Optional<User> findByUsername(String username);

  int deleteByUsername(String username);
}