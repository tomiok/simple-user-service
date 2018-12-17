package org.tommy.userservice.adapters;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.tommy.userservice.usecase.CreateUserService;
import org.tommy.userservice.usecase.DeleteUserService;
import org.tommy.userservice.usecase.FindUserService;
import org.tommy.userservice.usecase.UpdateUserService;
import org.tommy.userservice.usecase.UseCasesConfig;

@Configuration
@Import(UseCasesConfig.class)
public class UserServiceConfig {

  @Bean
  public UserService userService(
      final CreateUserService createUserService,
      final FindUserService findUserService,
      final UpdateUserService updateUserService,
      final DeleteUserService deleteUserService
  ) {
    return new UserServiceImpl(createUserService, findUserService, updateUserService, deleteUserService);
  }
}
