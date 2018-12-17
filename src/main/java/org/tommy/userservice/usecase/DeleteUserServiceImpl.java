package org.tommy.userservice.usecase;

import javax.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.tommy.userservice.model.UserRepo;

class DeleteUserServiceImpl implements DeleteUserService {

  private static final Logger log = LoggerFactory.getLogger(DeleteUserServiceImpl.class);

  private UserRepo userRepo;

  DeleteUserServiceImpl(final UserRepo userRepo) {
    this.userRepo = userRepo;
  }

  @Transactional
  @Override
  public void deleteUser(final String username) {
    int rows = userRepo.deleteByUsername(username);
    if (rows == 0) {
      log.warn("username {} is not in the DB.", username);
      throw new EntityNotFoundException("Username not found in the DB");
    }
  }
}
