package org.tommy.userservice.usecase;

import org.tommy.userservice.model.User;

public interface FindUserService {

  User findByUsername(String username);
}
