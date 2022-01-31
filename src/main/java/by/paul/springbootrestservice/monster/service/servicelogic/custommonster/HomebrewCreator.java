package by.paul.springbootrestservice.monster.service.servicelogic.custommonster;

import by.paul.springbootrestservice.monster.entity.Monster;

public interface HomebrewCreator {
  boolean uniqueChecker(String monsterName, String monsterOwner);
  void addMonster(Monster monster);
}
