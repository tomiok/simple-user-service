package org.tommy.userservice.web;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.tommy.userservice.usecase.CreateUserServiceImplTest.newCmd;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.tommy.userservice.adapters.UserService;
import org.tommy.userservice.model.User;
import org.tommy.userservice.model.UserStatus;
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

    when(userService.createUser(cmd)).thenReturn(new User());

    mockMvc.perform(post("/users")
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(objectMapper.writeValueAsString(cmd)))
        .andExpect(status().is(HttpStatus.CREATED.value()));
  }

  @Test
  public void findUser() throws Exception {
    String username = "tomi";

    when(userService.findUserByUsername(username)).thenReturn(new User(username, "tomas",
        "lingotti", "tomi@mas.com", "pass1234", "34177889900", UserStatus.ACTIVE));
    mockMvc.perform(get("/users/{username}", username))
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$.username", is("tomi")))
        .andExpect(jsonPath("$.firstName", is("tomas")))
        .andExpect(jsonPath("$.lastName", is("lingotti")))
        .andExpect(jsonPath("$.email", is("tomi@mas.com")))
        .andExpect(jsonPath("$.phone", is("34177889900")));

  }

  @Test
  public void deleteUser() throws Exception {
    String username = "tomi";
    mockMvc.perform(delete("/users/{username}", username)
        .contentType(MediaType.APPLICATION_JSON_UTF8))
        .andExpect(status().is(HttpStatus.OK.value()));
  }

  @Test
  public void updateUser() throws Exception {
    String username = "tomi";
    String firstName = "tomas";
    String lastName = "lingotti";
    String phone = "3415491538";
    String email = "tomi@msn.com";
    String pass = "admin123";
    CreateUserService.CreateOrUpdateUserCommand cmd
        = newCmd(email, username, firstName, lastName, pass, phone);

    when(userService.updateUser(username, cmd)).thenReturn(new User(username, "tomas",
        "lingotti", "tomi@mas.com", "pass1234", "34177889900", UserStatus.ACTIVE));
    mockMvc.perform(put("/users/{username}", username)
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .content(objectMapper.writeValueAsString(cmd)))
        .andExpect(status().is2xxSuccessful())
        .andExpect(jsonPath("$.message", is("User modified")));
  }
}