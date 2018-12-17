package org.tommy.userservice.adapters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tommy.userservice.model.User;
import org.tommy.userservice.usecase.CreateUserService;
import org.tommy.userservice.usecase.DeleteUserService;
import org.tommy.userservice.usecase.FindUserService;
import org.tommy.userservice.usecase.UpdateUserService;

class UserServiceImpl implements UserService {

  private final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

  private final CreateUserService createUserService;

  private final FindUserService findUserService;

  private final UpdateUserService updateUserService;

  private final DeleteUserService deleteUserService;

  UserServiceImpl(final CreateUserService createUserService,
                  final FindUserService findUserService,
                  final UpdateUserService updateUserService,
                  final DeleteUserService deleteUserService) {
    this.createUserService = createUserService;
    this.findUserService = findUserService;
    this.updateUserService = updateUserService;
    this.deleteUserService = deleteUserService;
  }

  @Override
  public User createUser(final CreateUserService.CreateOrUpdateUserCommand cmd) {
    log.info("Creating user");
    return createUserService.createUser(cmd);
  }

  @Override
  public User findUserByUsername(final String username) {
    log.info("Retrieving user with username {}", username);
    return findUserService.findByUsername(username);
  }

  @Override
  public User updateUser(final String username, final CreateUserService.CreateOrUpdateUserCommand cmd) {
    log.info("Updating user {}", username);
    return updateUserService.updateUser(cmd, username);
  }

  @Override
  public void deleteUserByUsername(final String username) {
    log.info("Deleting user {}", username);
    deleteUserService.deleteUser(username);
  }
}
