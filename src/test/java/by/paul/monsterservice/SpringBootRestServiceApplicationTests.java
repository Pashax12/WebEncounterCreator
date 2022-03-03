package by.paul.monsterservice;


import by.paul.monsterservice.entity.EncounterBuilder;
import by.paul.monsterservice.entity.GeneratedMonsterDTO;
import by.paul.monsterservice.entity.Monster;
import by.paul.monsterservice.service.dto.MonsterMapper;
import by.paul.monsterservice.service.servicelogic.generator.ExpCounter;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = SpringBootRestServiceApplicationTests.class)
class SpringBootRestServiceApplicationTests {

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
    Assertions.assertEquals(expCounter
            .getHoleFightExp(encounterBuilder.getPlayersLevel(), encounterBuilder.getDifficulty()),
        3000);
  }

}
