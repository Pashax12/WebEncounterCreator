package by.paul.springbootrestservice.monsterService.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class EncounterBuilder {

  @JsonProperty("monsterOwner")
  private String monsterOwner;
  @JsonProperty("difficulty")
  private String difficulty;
  @JsonProperty("playersLevel")
  private List<String> playersLevel;
  @JsonProperty("mixedTypes")
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


}
