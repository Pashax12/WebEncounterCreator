package by.paul.springbootrestservice.monster.entity;

import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@Scope("singleton")
@Component
public class EncounterBuilder {

  private String difficulty;
  private List<String> playersLevel = new ArrayList<>();
  private boolean mixedTypes;

  public int getHoleFightExp() {
    int expCounter = 0;
    for (Integer level : parseInteger()) {
      expCounter += DifficultyEnum.valueOf(difficulty).getExpLevel(level);
    }
    return expCounter;
  }

  private List<Integer> parseInteger() {
    List<Integer> levels = new ArrayList<>();
    playersLevel.forEach(s -> levels.add(Integer.parseInt(s.split(" ")[0])));
    return levels;
  }

  public void addPlayer(String player) {
    playersLevel.add(player);
  }
}
