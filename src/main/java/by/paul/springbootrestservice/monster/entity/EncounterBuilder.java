package by.paul.springbootrestservice.monster.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class EncounterBuilder {

  @JsonProperty("monsterOwner")
  @NotBlank
  private String monsterOwner;
  @JsonProperty("difficulty")
  @NotBlank
  private String difficulty;
  @JsonProperty("playersLevel")
  @NotBlank
  private List<String> playersLevel;
  @JsonProperty("mixedTypes")
  @NotBlank
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
