package by.paul.springbootrestservice.monster.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class SearcherCriteria {
  private String monsterName;
  private String monsterType;
  private String monsterSize;
  private int minMonsterChallenge;
  private int maxMonsterChallenge;
  private String monsterOutlook;
  private String owner;
  private boolean source;
  private boolean legendaryAction;
  private boolean specialSkills;
  private int minMonsterSpeed;
  private int maxMonsterSpeed;

}
