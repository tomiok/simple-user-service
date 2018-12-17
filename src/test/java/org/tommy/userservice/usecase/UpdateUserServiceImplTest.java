package org.tommy.userservice.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.tommy.userservice.usecase.CreateUserServiceImplTest.newCmd;

import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.tommy.userservice.model.User;
import org.tommy.userservice.model.UserRepo;
import org.tommy.userservice.model.UserStatus;

@RunWith(MockitoJUnitRunner.class)
public class UpdateUserServiceImplTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Mock
  private UserRepo repository;

  @InjectMocks
  private UpdateUserServiceImpl updateUserService;

  @Test
  public void updateUser() {
    String username = "tomi";
    String firstName = "tomas";
    String lastName = "lingotti";
    String phone = "3415491538";
    String email = "tomi@msn.com";
    String pass = "admin123";

    User u = new User(username, firstName, lastName, email, pass, phone, UserStatus.ACTIVE);

    when(repository.findByUsername(username)).thenReturn(Optional.of(u));

    when(repository.save(any(User.class))).thenReturn(new User(username, firstName, lastName, "tomas@gmail.com",
        pass, "3415990990", UserStatus.BLOCKED));
    User actual = updateUserService.updateUser(newCmd("tomas@gmail.com", "tomi", "tomas", "lingotti",
        null, "3415990990"), "tomi");

    assertThat(actual.getEmail()).isEqualTo("tomas@gmail.com");
    assertThat(actual.getFirstName()).isEqualTo(firstName);
    assertThat(actual.getLastName()).isEqualTo(lastName);
    assertThat(actual.getUserStatus()).isEqualByComparingTo(UserStatus.BLOCKED);
    assertThat(actual.getPhone()).isEqualTo("3415990990");
    assertThat(actual.getPassword()).isNotEmpty();

    verify(repository, times(1)).findByUsername(username);
    verify(repository, times(1)).save(any(User.class));
  }

  @Test
  public void shouldFail_GivenWrongUsername() {
    thrown.expect(EntityNotFoundException.class);
    updateUserService.updateUser(null, "tomasf");
  }
}