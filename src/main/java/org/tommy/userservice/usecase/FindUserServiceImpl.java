package org.tommy.userservice.usecase;

import javax.persistence.EntityNotFoundException;
import org.tommy.userservice.model.User;
import org.tommy.userservice.model.UserRepo;

class FindUserServiceImpl implements FindUserService {

  private final UserRepo userRepo;

  FindUserServiceImpl(final UserRepo userRepo) {
    this.userRepo = userRepo;
  }

  @Override
  public User findByUsername(final String username) {
    return userRepo.findByUsername(username)
        .orElseThrow(() -> new EntityNotFoundException("User not found in the DB"));
  }
}
