package org.tommy.userservice.usecase;

import lombok.Getter;
import lombok.Setter;
import org.tommy.userservice.model.User;
import org.tommy.userservice.model.UserStatus;

public interface CreateUserService {

  User createUser(CreateOrUpdateUserCommand cmd);

  @Getter
  @Setter
  class CreateOrUpdateUserCommand {
    private String username;
    private String firstName;
    private String lastName;
    private String password;
    private String email;
    private String phone;
    private UserStatus userStatus;
  }
}
