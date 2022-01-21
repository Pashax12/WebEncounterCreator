package by.paul.springbootrestservice.monster.service.generator;


import by.paul.springbootrestservice.monster.entity.Monster;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MonsterMessageGenerator {

  public String monstersListGenerator(List<Monster> possibleMonsterList,
      boolean battleType, int fightExp) {
    return monstersMessageGenerator(battleType ? singleBattle(possibleMonsterList, fightExp) : mixedBattle(possibleMonsterList, fightExp));
  }

  List<Monster> mixedBattle(List<Monster> possibleMonsterList,int fightExp ){
    List<Monster> generatedList= new ArrayList<>();
    while (fightExp >=0){
      Monster monster = possibleMonsterList.get((int) (Math.random() * possibleMonsterList.size()));
      generatedList.add(monster);
      fightExp-= monster.getMonsterChallenge();
    }
    return generatedList;
  }
  List<Monster> singleBattle(List<Monster> possibleMonsterList,int fightExp){
    List<Monster> generatedList= new ArrayList<>();
    Monster monster = possibleMonsterList.get((int) (Math.random() * possibleMonsterList.size()));
    while (fightExp >=0){
      generatedList.add(monster);
      fightExp-= monster.getMonsterChallenge();
    }
    return generatedList;
  }

  @Value("${userMessage.generatedMessage.startMes} \n")
  String startMes;

  @Value("${userMessage.generatedMessage.endMesOne} \n ${userMessage.generatedMessage.endMesTwo} \n")
  String endMes;

  public String monstersMessageGenerator(List<Monster> possibleMonsterList) {
    possibleMonsterList.forEach(monsters -> startMes+=monsters.toString());
    return startMes +endMes;
  }
}
