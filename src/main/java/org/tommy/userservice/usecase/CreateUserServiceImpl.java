package org.tommy.userservice.usecase;

import org.apache.commons.codec.digest.DigestUtils;
import org.tommy.userservice.model.User;
import org.tommy.userservice.model.UserRepo;

class CreateUserServiceImpl implements CreateUserService {

  private UserRepo userRepo;

  CreateUserServiceImpl(final UserRepo userRepo) {
    this.userRepo = userRepo;
  }

  @Override
  public User createUser(final CreateOrUpdateUserCommand cmd) {
    User user = new User(cmd.getUsername(),
        cmd.getFirstName(), cmd.getLastName(), cmd.getEmail(), hashPassword(cmd.getPassword()),
        cmd.getPhone(), cmd.getUserStatus());

    return userRepo.save(user);
  }

  private String hashPassword(String pass) {
    return DigestUtils.sha256Hex(pass);
  }
}
