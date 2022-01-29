package by.paul.springbootrestservice;


import by.paul.springbootrestservice.monster.entity.EncounterBuilder;
import by.paul.springbootrestservice.monster.entity.Monster;
import by.paul.springbootrestservice.monster.service.dto.GeneratedMonsterDTO;
import by.paul.springbootrestservice.monster.service.dto.MonsterMapper;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
class SpringBootRestServiceApplicationTests {


  private MockMvc mockMvc;


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

  @Test
  void expCounter() {
    EncounterBuilder encounterBuilder = new EncounterBuilder();
    List<String> strings = new ArrayList<>();
    strings.add("5 level bard");
    strings.add("5 level bard");
    strings.add("5 level bard");
    strings.add("5 level bard");
    encounterBuilder.setPlayersLevel(strings);
    encounterBuilder.setDifficulty("HARD");
    encounterBuilder.setMonsterOwner("admin");
    encounterBuilder.setMixedTypes(true);
    Assertions.assertEquals(encounterBuilder.getHoleFightExp(), 3000);
  }


}
