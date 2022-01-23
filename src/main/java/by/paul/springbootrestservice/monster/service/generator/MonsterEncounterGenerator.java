package by.paul.springbootrestservice.monster.service.generator;


import by.paul.springbootrestservice.monster.entity.Monster;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class MonsterEncounterGenerator {

  public List<Monster> monstersListGenerator(List<Monster> possibleMonsterList,
      boolean battleType, int fightExp) {
    return battleType ? singleBattle(possibleMonsterList, fightExp)
        : mixedBattle(possibleMonsterList, fightExp);
  }

  List<Monster> mixedBattle(List<Monster> possibleMonsterList, int fightExp) {
    List<Monster> generatedList = new ArrayList<>();
    while (fightExp >= 0) {
      Monster monster = possibleMonsterList.get((int) (Math.random() * possibleMonsterList.size()));
      generatedList.add(monster);
      fightExp -= monster.getMonsterChallenge();
    }
    return generatedList;
  }

  List<Monster> singleBattle(List<Monster> possibleMonsterList, int fightExp) {
    List<Monster> generatedList = new ArrayList<>();
    Monster monster = possibleMonsterList.get((int) (Math.random() * possibleMonsterList.size()));
    while (fightExp >= 0) {
      generatedList.add(monster);
      fightExp -= monster.getMonsterChallenge();
    }
    return generatedList;
  }
}
