package org.tommy.userservice.usecase;

import javax.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.tommy.userservice.model.User;
import org.tommy.userservice.model.UserRepo;

class UpdateUserServiceImpl implements UpdateUserService {

  private static final Logger log = LoggerFactory.getLogger(UpdateUserServiceImpl.class);

  private final UserRepo userRepo;

  UpdateUserServiceImpl(final UserRepo userRepo) {
    this.userRepo = userRepo;
  }

  @Transactional
  @Override
  public User updateUser(final CreateUserService.CreateOrUpdateUserCommand cmd, String username) {
    User user = userRepo.findByUsername(username)
        .orElseThrow(() -> new EntityNotFoundException("Cannot update user. Not found in the DB"));
    log.info("Updating user {}", username);

    user.setFirstName(cmd.getFirstName());
    user.setLastName(cmd.getLastName());
    user.setEmail(cmd.getEmail());
    user.setPhone(cmd.getPhone());
    user.setUsername(username);
    user.setUserStatus(cmd.getUserStatus());

    return userRepo.save(user);
  }
}
