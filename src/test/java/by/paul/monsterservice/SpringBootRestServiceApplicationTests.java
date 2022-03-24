package by.paul.monsterservice;


import by.paul.monsterservice.config.ApplicationTestConfig;
import by.paul.monsterservice.entity.DifficultyEnum;
import by.paul.monsterservice.entity.EncounterSettings;
import by.paul.monsterservice.service.monster.ExpCounter;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@SpringBootTest(classes = SpringBootRestServiceApplicationTests.class)
@Import(ApplicationTestConfig.class)
class SpringBootRestServiceApplicationTests {

  private static EncounterSettings encounterSettings;

  @Autowired
  private ExpCounter expCounter;


  @BeforeAll
  static void init() {
    List<String> strings = new ArrayList<>();
    strings.add("5 level bard");
    strings.add("5 level bard");
    strings.add("5 level bard");
    strings.add("5 level bard");
    encounterSettings = new EncounterSettings();
    encounterSettings.setPlayersLevel(strings);
  }


  @Test
  void HardDifficultyWithPlayerExperienceCounter() {
    Assertions.assertEquals(expCounter
            .getHoleFightExp(encounterSettings.getPlayersLevel(), String.valueOf(DifficultyEnum.HARD)),
        3000);
  }

  @Test
  void EasyDifficultyWithPlayerExperienceCounter() {
    Assertions.assertEquals(expCounter
            .getHoleFightExp(encounterSettings.getPlayersLevel(), String.valueOf(DifficultyEnum.EASY)),
        1000);
  }

  @Test
  void EasyDifficultyWithoutPlayerExperienceCounter() {
    Assertions.assertEquals(expCounter
        .getHoleFightExp(new ArrayList<>(), String.valueOf(DifficultyEnum.EASY)), 0);
  }

}
