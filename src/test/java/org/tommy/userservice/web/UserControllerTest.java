package org.tommy.userservice.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.tommy.userservice.usecase.CreateUserServiceImplTest.newCmd;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.tommy.userservice.adapters.UserService;
import org.tommy.userservice.model.User;
import org.tommy.userservice.usecase.CreateUserService;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userService;

  private ObjectMapper objectMapper = new ObjectMapper();

  @Test
  public void saveUser() throws Exception {
    String username = "tomi";
    String firstName = "tomas";
    String lastName = "lingotti";
    String phone = "3415491538";
    String email = "tomi@msn.com";
    String pass = "admin123";
    CreateUserService.CreateOrUpdateUserCommand cmd
        = newCmd(email, username, firstName, lastName, pass, phone);

    Mockito.when(userService.createUser(cmd)).thenReturn(new User());

    mockMvc.perform(post("/users")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(objectMapper.writeValueAsString(cmd)))
        .andExpect(status().is(HttpStatus.CREATED.value()));
  }

  @Test
  public void findUser() {
  }

  @Test
  public void deleteUser() {
  }

  @Test
  public void updateUser() {
  }
}