package org.tommy.userservice.usecase;

import org.tommy.userservice.model.User;

public interface UpdateUserService {

  /**
   * Find the user by username and update the fields. The password will not be updated.
   * @param cmd The command to update the user
   * @param username The key to find the user
   * @return The user updated.
   */
  User updateUser(CreateUserService.CreateOrUpdateUserCommand cmd, String username);

}
