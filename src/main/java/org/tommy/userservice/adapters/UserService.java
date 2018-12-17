package org.tommy.userservice.adapters;

import org.tommy.userservice.model.User;
import org.tommy.userservice.usecase.CreateUserService;

/**
 * Class to agglomerate the user's use cases.
 */
public interface UserService {

  User createUser(CreateUserService.CreateOrUpdateUserCommand cmd);

  User findUserByUsername(String username);

  User updateUser(String username, CreateUserService.CreateOrUpdateUserCommand cmd);

  void deleteUserByUsername(String username);
}
