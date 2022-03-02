package by.paul.springbootrestservice;



import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import by.paul.monsterservice.entity.EncounterBuilder;
import by.paul.monsterservice.entity.Monster;
import by.paul.monsterservice.entity.GeneratedMonsterDTO;
import by.paul.monsterservice.service.dto.MonsterMapper;
import by.paul.monsterservice.service.servicelogic.generator.ExpCounter;
import java.util.ArrayList;
import java.util.List;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = SpringBootRestServiceApplicationTests.class)
class SpringBootRestServiceApplicationTests {

  @Autowired
  private MockMvc mockMvc;
  @Mock
  private ExpCounter expCounter;


  @Test
  void dtoConverterTest() {
    Monster monster = new Monster();
    monster.setMonsterName("RedDragon");
    monster.setMonsterMeta("Monster for test");
    monster.setMonsterOwner("Paul");
    monster.setMonsterChallenge(1200);
    GeneratedMonsterDTO generatedMonsterDTO = MonsterMapper.INSTANCE.monsterToMonsterDto(monster);
    Assertions.assertAll(
        () -> Assertions
            .assertEquals(monster.getMonsterName(), (generatedMonsterDTO.getMonsterName())),
        () -> Assertions
            .assertEquals(monster.getMonsterMeta(), (generatedMonsterDTO.getMonsterMeta())),
        () -> Assertions.assertEquals(monster.getMonsterChallenge(),
            (generatedMonsterDTO.getMonsterChallenge()))
    );
  }
//Можно поиграться с Мок
  @Test
  void expCounter() {
    expCounter = new ExpCounter();
    EncounterBuilder encounterBuilder = new EncounterBuilder();
    List<String> strings = new ArrayList<>();
    strings.add("5 level bard");
    strings.add("5 level bard");
    strings.add("5 level bard");
    strings.add("5 level bard");
    encounterBuilder.setPlayersLevel(strings);
    encounterBuilder.setDifficulty("HARD");
    encounterBuilder.setMixedTypes(true);
    Assertions.assertEquals(expCounter.getHoleFightExp(encounterBuilder.getPlayersLevel(), encounterBuilder.getDifficulty()), 3000);
  }


  @Test
  @SneakyThrows
  void getMonsters() {
    this.mockMvc.perform(get("/monsterlibrary/Aboleth")
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().is(302))
        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.name").value("Ape"));
  }

  @Test
  @SneakyThrows
  void getEncounter(){
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
        .andExpect(status().is(302))
        .andExpect(content()
            .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
  }

}
