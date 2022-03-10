package by.paul.monsterservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GeneratedMonsterDTO {

  @JsonProperty("monsterName")
  String monsterName;
  @JsonProperty("monsterMeta")
  String monsterMeta;
  @JsonProperty("monsterChallenge")
  int monsterChallenge;
}
