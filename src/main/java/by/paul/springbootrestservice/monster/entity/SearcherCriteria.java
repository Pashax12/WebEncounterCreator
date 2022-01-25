package by.paul.springbootrestservice.monster.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class SearcherCriteria {
  @JsonProperty("monsterType")
  private String monsterType;
  @JsonProperty("monsterSize")
  private String monsterSize;
  @JsonProperty("minMonsterChallenge")
  private int minMonsterChallenge;
  @JsonProperty("maxMonsterChallenge")
  private int maxMonsterChallenge;
  @JsonProperty("monsterOutlook")
  private String monsterOutlook;
  @JsonProperty("source")
  private boolean source;
  @JsonProperty("legendaryAction")
  private boolean legendaryAction;
  @JsonProperty("specialSkills")
  private boolean specialSkills;
  @JsonProperty("minMonsterSpeed")
  private int minMonsterSpeed;
  @JsonProperty("maxMonsterSpeed")
  private int maxMonsterSpeed;

}
