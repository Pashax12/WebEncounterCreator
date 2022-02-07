package by.paul.monsterservice.service.servicelogic.library;

import by.paul.monsterservice.entity.Monster;
import by.paul.monsterservice.entity.SearcherCriteria;
import by.paul.monsterservice.service.dto.GeneratedMonsterDTO;
import java.util.List;

public interface MonsterLibrary {

  List<Monster> getMonsterByName(SearcherCriteria searcherCriteria);
  List<GeneratedMonsterDTO> getAllAuthorMonster(String authorName);
  Monster getMonsterByName(String monsterName);
}
