package by.paul.springbootrestservice.monster.service.servicelogic.library;

import by.paul.springbootrestservice.monster.entity.Monster;
import by.paul.springbootrestservice.monster.entity.SearcherCriteria;
import by.paul.springbootrestservice.monster.service.dto.GeneratedMonsterDTO;
import java.util.List;

public interface MonsterLibrary {

  List<Monster> getMonsterByName(SearcherCriteria searcherCriteria);
  List<GeneratedMonsterDTO> getAllAuthorMonster(String authorName);
  Monster getMonsterByName(String monsterName);
}
