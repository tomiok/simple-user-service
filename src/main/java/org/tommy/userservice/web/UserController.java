package org.tommy.userservice.web;

import static org.springframework.http.ResponseEntity.ok;
import static org.tommy.userservice.web.ServiceResponse.generateResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tommy.userservice.adapters.UserService;
import org.tommy.userservice.model.User;
import org.tommy.userservice.usecase.CreateUserService;

@RestController
@RequestMapping("/users")
public class UserController {

  private final UserService userService;

  public UserController(final UserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public ResponseEntity<ServiceResponse> saveUser(@RequestBody CreateUserService.CreateOrUpdateUserCommand cmd) {
    userService.createUser(cmd);
    return generateResponse("User created", HttpStatus.CREATED);
  }

  @GetMapping("/{username}")
  public ResponseEntity<UserSummary> findUser(@PathVariable String username) {
    User user = userService.findUserByUsername(username);
    UserSummary userSummary = UserSummary.fromUser(user);
    return ok(userSummary);
  }

  @DeleteMapping("/{username}")
  public ResponseEntity<ServiceResponse> deleteUser(@PathVariable String username) {
    userService.deleteUserByUsername(username);
    return generateResponse("User deleted", HttpStatus.OK);
  }

  @PutMapping("/{username}")
  public ResponseEntity<ServiceResponse> updateUser(
      @PathVariable String username, @RequestBody CreateUserService.CreateOrUpdateUserCommand cmd) {
    userService.updateUser(username, cmd);
    return generateResponse("User modified", HttpStatus.OK);
  }
}
