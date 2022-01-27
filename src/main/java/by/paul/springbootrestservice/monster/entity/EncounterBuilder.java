package by.paul.springbootrestservice.monster.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class EncounterBuilder {

  @JsonProperty("monsterOwner")
  @NotNull(message = "monsterOwner can't be null")
  private String monsterOwner;
  @JsonProperty("difficulty")
  @NotNull(message = "monsterOwner can't be null")
  private String difficulty;
  @JsonProperty("playersLevel")
  @NotNull(message = "monsterOwner can't be null")
  private List<String> playersLevel;
  @JsonProperty("mixedTypes")
  @NotNull(message = "monsterOwner can't be null")
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
