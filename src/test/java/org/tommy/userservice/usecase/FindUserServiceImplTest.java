package org.tommy.userservice.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.tommy.userservice.model.User;
import org.tommy.userservice.model.UserRepo;
import org.tommy.userservice.model.UserStatus;

@RunWith(MockitoJUnitRunner.class)
public class FindUserServiceImplTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Mock
  private UserRepo repository;

  @InjectMocks
  private FindUserServiceImpl findUserService;

  @Test
  public void findByUsername() {
    String username = "tomi";
    String firstName = "tomas";
    String lastName = "lingotti";
    String phone = "3415491538";
    String email = "tomi@msn.com";
    String pass = "admin123";

    User u = new User(username, firstName, lastName, email, pass, phone, UserStatus.ACTIVE);

    when(repository.findByUsername(username)).thenReturn(Optional.of(u));

    User actual = findUserService.findByUsername(username);

    assertThat(actual.getEmail()).isEqualTo(email);
    assertThat(actual.getFirstName()).isEqualTo(firstName);
    assertThat(actual.getLastName()).isEqualTo(lastName);
    assertThat(actual.getUserStatus()).isEqualByComparingTo(UserStatus.ACTIVE);
    assertThat(actual.getPhone()).isEqualTo(phone);
    assertThat(actual.getPassword()).isNotEmpty();

    verify(repository, times(1)).findByUsername(username);
  }

  @Test
  public void shouldFail_GivenWrongUsername() {
    thrown.expect(EntityNotFoundException.class);
    findUserService.findByUsername("tomiok");

  }
}