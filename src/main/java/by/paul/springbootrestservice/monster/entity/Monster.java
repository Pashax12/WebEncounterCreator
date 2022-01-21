package by.paul.springbootrestservice.monster.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

import org.springframework.stereotype.Component;

@Data
@Entity
@Table(name = "monster_list")
@Component
public class Monster {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long monsterId;
  @Column
  @JsonProperty("name")
  private String monsterName;
  @Column
  @JsonProperty("meta")
  private String monsterMeta;
  @Column
  @JsonProperty("Armor Class")
  private String monsterArmour;
  @Column
  @JsonProperty("Hit Points")
  private String monsterHP;
  @Column
  @JsonProperty("Speed")
  private String monsterSpeed;

  @Column
  @JsonProperty("STR")
  private String monsterSTR;
  @Column
  @JsonProperty("STR_mod")
  private String monsterSTR_mod;
  @Column
  @JsonProperty("DEX")
  private String monsterDEX;
  @Column
  @JsonProperty("DEX_mod")
  private String monsterDEX_mod;
  @Column
  @JsonProperty("CON")
  private String monsterCON;
  @Column
  @JsonProperty("CON_mod")
  private String monsterCON_mod;
  @Column
  @JsonProperty("INT")
  private String monsterINT;
  @Column
  @JsonProperty("INT_mod")
  private String monsterINT_mod;
  @Column
  @JsonProperty("WIS")
  private String monsterWIS;
  @Column
  @JsonProperty("WIS_mod")
  private String monsterWIS_mod;
  @Column
  @JsonProperty("CHA")
  private String monsterCHA;
  @Column
  @JsonProperty("CHA_mod")
  private String monsterCHA_mod;

  @Column
  @JsonProperty("Saving Throws")
  private String monsterSavingTrows;
  @Column
  @JsonProperty("Skills")
  private String monsterSkills;
  @Column
  @JsonProperty("Senses")
  private String monsterSenses;
  @Column
  @JsonProperty("Languages")
  private String monsterLanguages;
  @Column
  @JsonProperty("Challenge")
  private int monsterChallenge;
  @Column
  @JsonProperty("Traits")
  private String monsterTraits;
  @Column
  @JsonProperty("Actions")
  private String monsterActions;
  @Column
  @JsonProperty("Legendary Actions")
  private String monsterLegendaryActions;
  @Column
  @JsonProperty("img_url")
  private String monsterPic;
  @Column
  private String monsterOwner;

}
