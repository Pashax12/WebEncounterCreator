package by.paul.monsterservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SearcherCriteria {

  @JsonProperty("monsterType")
  @NotBlank(message = "monsterType can't be blank please")
  private String monsterType;
  @JsonProperty("monsterSize")
  @NotBlank(message = "monsterType can't be blank")
  private String monsterSize;
  @JsonProperty("minMonsterChallenge")
  @Min(value = 0, message = "MonsterChallenge not be less than 0")
  @Max(value = 155000, message = "MonsterChallenge not be greater than 155000")
  private int minMonsterChallenge;
  @JsonProperty("maxMonsterChallenge")
  @Min(value = 0, message = "MonsterChallenge not be less than 0")
  @Max(value = 155000, message = "MonsterChallenge not be greater than 155000")
  private int maxMonsterChallenge;
  @JsonProperty("monsterOutlook")
  @NotBlank(message = "monsterType can't be blank")
  private String monsterOutlook;
  @JsonProperty("source")
  private boolean source;
  @JsonProperty("legendaryAction")
  private boolean legendaryAction;
  @JsonProperty("specialSkills")
  private boolean specialSkills;
}
