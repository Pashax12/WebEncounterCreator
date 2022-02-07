package by.paul.monsterservice.entity;

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
  @NotNull(message = "fight difficulty can't be null")
  private String difficulty;
  @JsonProperty("playersLevel")
  @NotNull(message = "playersLevel can't be null")
  private List<String> playersLevel;
  @JsonProperty("mixedTypes")
  @NotNull(message = "mixedTypes can't be null")
  private boolean mixedTypes;


}
