package by.paul.monsterservice.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.stereotype.Component;

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
  @JsonProperty("monsterPath")
  @NotBlank
  String monsterPath;
}
