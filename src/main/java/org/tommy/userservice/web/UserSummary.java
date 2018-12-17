package org.tommy.userservice.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.tommy.userservice.model.User;
import org.tommy.userservice.model.UserStatus;

@Getter
@Setter
@AllArgsConstructor
class UserSummary {

  private String username;

  private String firstName;

  private String lastName;

  private String email;

  private String phone;

  private UserStatus userStatus;

  static UserSummary fromUser(User user) {
    return new UserSummary(user.getUsername(), user.getFirstName(), user.getLastName(),
        user.getEmail(), user.getPhone(), user.getUserStatus());
  }
}
