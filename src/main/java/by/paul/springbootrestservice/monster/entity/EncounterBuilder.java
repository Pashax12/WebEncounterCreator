package by.paul.springbootrestservice.monster.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
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
    return parseInteger().stream()
        .mapToInt(integer -> DifficultyEnum.valueOf(difficulty).getExpLevel(integer)).sum();
  }

  private List<Integer> parseInteger() {
    return playersLevel.stream().map(s -> Integer.parseInt(s.split(" ")[0]))
        .collect(Collectors.toList());
  }


}
