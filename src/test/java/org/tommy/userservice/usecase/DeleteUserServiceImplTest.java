package org.tommy.userservice.usecase;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.persistence.EntityNotFoundException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.tommy.userservice.model.UserRepo;

@RunWith(MockitoJUnitRunner.class)
public class DeleteUserServiceImplTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  @Mock
  private UserRepo repo;

  @InjectMocks
  private DeleteUserServiceImpl deleteUserService;

  @Test
  public void deleteUser() {
    String username = "tomiok";
    when(repo.deleteByUsername(username)).thenReturn(1);

    deleteUserService.deleteUser(username);
    verify(repo, times(1)).deleteByUsername(username);
  }

  @Test
  public void shouldFail_GivenWrongUsername() {
    thrown.expect(EntityNotFoundException.class);
    String username = "unknown";
    when(repo.deleteByUsername(username)).thenReturn(0);

    deleteUserService.deleteUser(username);
    verify(repo, times(1)).deleteByUsername(username);
  }
}