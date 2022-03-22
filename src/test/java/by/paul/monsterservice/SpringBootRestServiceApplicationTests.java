package by.paul.monsterservice;


import by.paul.monsterservice.entity.EncounterSetup;
import by.paul.monsterservice.service.monster.ExpCounter;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(classes = SpringBootRestServiceApplicationTests.class)
@EnableConfigurationProperties
class SpringBootRestServiceApplicationTests {

  private static EncounterSetup encounterSetup;
  @Mock
  private ExpCounter expCounter;



  @BeforeAll
  static void init() {
    List<String> strings = new ArrayList<>();
    strings.add("5 level bard");
    strings.add("5 level bard");
    strings.add("5 level bard");
    strings.add("5 level bard");
    encounterSetup = new EncounterSetup();
    encounterSetup.setPlayersLevel(strings);
  }


  @Test
  void expCounter() {
    Mockito.when(expCounter
        .getHoleFightExp(encounterSetup.getPlayersLevel(), "HARD"))
        .thenReturn(3000);
    Mockito.when(expCounter
        .getHoleFightExp(encounterSetup.getPlayersLevel(), "EASY"))
        .thenReturn(1000);
    Mockito.when(expCounter
        .getHoleFightExp(encounterSetup.getPlayersLevel(), "DEADLY"))
        .thenReturn(4400);
    Mockito.when(expCounter
        .getHoleFightExp(null, "HARD")).thenReturn(100);
  }
}
