package by.paul.monsterservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GeneratedMonsterDTO {

  @JsonProperty("monsterName")
  @NotBlank
  String monsterName;
  @NotBlank
  @JsonProperty("monsterMeta")
  String monsterMeta;
  @NotBlank
  @JsonProperty("monsterChallenge")
  int monsterChallenge;

}
