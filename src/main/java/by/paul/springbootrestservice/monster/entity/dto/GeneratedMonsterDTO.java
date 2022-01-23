package by.paul.springbootrestservice.monster.entity.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class GeneratedMonsterDTO {
  @JsonProperty("monsterName")
  String monsterName;
  @JsonProperty("monsterMeta")
  String monsterMeta;
  @JsonProperty("monsterChallenge")
  int monsterChallenge;
  @JsonProperty("monsterPath")
  String monsterPath;
}
