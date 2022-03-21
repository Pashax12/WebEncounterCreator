package by.paul.monsterservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class RegistrationControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @SneakyThrows
  void signup() {
    String userUnuique = "{\"email\": \"testUser3@gmail.com\", \"password\": \"82c5162e51af89203828574aa6c3677107d361eb9e3a5f831b1f5f2facc235e0\", \"name\": \"testUser3\"}";

    this.mockMvc.perform(post("/auth/singup")
        .contentType(MediaType.APPLICATION_JSON)
        .content(userUnuique))
        .andExpect(status().isOk())
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.TEXT_PLAIN_VALUE));
  }
  @Test
  @SneakyThrows
  void signin() {
    String userPas = "{\"email\": \"admin@gmail.com\", \"password\": \"admin\"}";
    String userForbidden = "{\"email\": \"admin@gmail.com\", \"password\": \"test\"}";

    this.mockMvc.perform(post("/auth/singin")
        .contentType(MediaType.APPLICATION_JSON)
        .content(userPas))
        .andExpect(status().is(200))
        .andExpect(jsonPath("$.email").value("admin@gmail.com"))
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON)).andDo(print());
    this.mockMvc.perform(post("/auth/singin")
        .contentType(MediaType.APPLICATION_JSON)
        .content(userForbidden))
        .andExpect(status().is(403))
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.TEXT_PLAIN_VALUE));
  }

}
