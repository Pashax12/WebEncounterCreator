package by.paul.monsterservice.service.servicelogic.custommonster;

import by.paul.monsterservice.entity.Monster;

public interface HomebrewCreator {
  boolean uniqueChecker(String monsterName);
  void addMonster(Monster monster);
}
