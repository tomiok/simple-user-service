package org.tommy.userservice.usecase;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.tommy.userservice.model.UserRepo;

@Configuration
public class UseCasesConfig {

  private final UserRepo userRepo;

  public UseCasesConfig(final UserRepo userRepo) {
    this.userRepo = userRepo;
  }

  @Bean
  public CreateUserService createUserService() {
    return new CreateUserServiceImpl(userRepo);
  }

  @Bean
  public DeleteUserService deleteUserService() {
    return new DeleteUserServiceImpl(userRepo);
  }

  @Bean
  public UpdateUserService updateUserService() {
    return new UpdateUserServiceImpl(userRepo);
  }

  @Bean
  public FindUserService findUserService() {
    return new FindUserServiceImpl(userRepo);
  }
}
