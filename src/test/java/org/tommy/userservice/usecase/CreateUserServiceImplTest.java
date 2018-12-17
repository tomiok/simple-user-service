package org.tommy.userservice.usecase;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.Times;
import org.mockito.junit.MockitoJUnitRunner;
import org.tommy.userservice.model.User;
import org.tommy.userservice.model.UserRepo;
import org.tommy.userservice.model.UserStatus;

@RunWith(MockitoJUnitRunner.class)
public class CreateUserServiceImplTest {

  @Mock
  private UserRepo repo;

  @InjectMocks
  private CreateUserServiceImpl createUserService;

  @Test
  public void createUser() {
    String username = "tomi";
    String firstName = "tomas";
    String lastName = "lingotti";
    String phone = "3415491538";
    String email = "tomi@msn.com";
    String pass = "admin123";

    User u = new User(username, firstName, lastName, email, pass, phone, UserStatus.ACTIVE);

    when(repo.save(any(User.class))).thenReturn(u);
    User actual = createUserService.createUser(newCmd(email, username, firstName, lastName, pass, phone));

    assertThat(actual.getEmail()).isEqualTo(email);
    assertThat(actual.getFirstName()).isEqualTo(firstName);
    assertThat(actual.getLastName()).isEqualTo(lastName);
    assertThat(actual.getUserStatus()).isEqualByComparingTo(UserStatus.ACTIVE);
    assertThat(actual.getPhone()).isEqualTo(phone);
    assertThat(actual.getPassword()).isNotEmpty();

    verify(repo, times(1)).save(any(User.class));
  }

  public static CreateUserService.CreateOrUpdateUserCommand newCmd(
      String email, String username, String firstName, String lastName, String pass, String phone
  ) {
    CreateUserService.CreateOrUpdateUserCommand cmd = new CreateUserService.CreateOrUpdateUserCommand();
    cmd.setEmail(email);
    cmd.setFirstName(firstName);
    cmd.setLastName(lastName);
    cmd.setPassword(pass);
    cmd.setUsername(username);
    cmd.setPhone(phone);
    cmd.setUserStatus(UserStatus.ACTIVE);

    return cmd;
  }
}