package by.paul.monsterservice;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
public class MonsterControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @SneakyThrows
  void getEncounter() {
    String encounter = "{\n"
        + "  \"monsterOwner\":\"admin\",\n"
        + "  \"difficulty\": \"HARD\",\n"
        + "  \"playersLevel\": [\"5 level priest\",\n"
        + "  \"5 level priest\",\n"
        + "  \"5 level priest\",\n"
        + "  \"5 level priest\"],\n"
        + "  \"mixedTypes\": false\n"
        + "}";

    this.mockMvc.perform(post("/createencounter")
        .contentType(MediaType.APPLICATION_JSON)
        .content(encounter))
        .andExpect(status().isOk())
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
  }

  @Test
  @SneakyThrows
  void searchCriteria() {
    String searchCriteria = "{\n"
        + "    \"monsterType\":\"\",\n"
        + "    \"monsterSize\":\"\",\n"
        + "    \"minMonsterChallenge\":\"0\",\n"
        + "    \"maxMonsterChallenge\":\"155000\",\n"
        + "    \"monsterOutlook\":\"\",\n"
        + "    \"source\":false,\n"
        + "    \"legendaryAction\":false,\n"
        + "    \"specialSkills\":true\n"
        + "}\n";

    this.mockMvc.perform(post("/monsterlibrary")
        .contentType(MediaType.APPLICATION_JSON)
        .content(searchCriteria))
        .andExpect(status().isOk())
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
  }

  @Test
  @SneakyThrows
  void getMonsterName() {
    this.mockMvc.perform(get("/monsterlibrary/Ape")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.name").value("Ape"))
        .andDo(print());
  }

  @Test
  @SneakyThrows
  void getMonsterAuthor() {
    this.mockMvc.perform(get("/monsterlibrary")
        .param("author", "admin")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.[0].monsterName").value("Aboleth"))
        .andDo(print());
  }
}
