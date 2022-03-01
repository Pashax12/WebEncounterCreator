package by.paul.monsterservice.service;

import by.paul.monsterservice.entity.EncounterBuilder;
import by.paul.monsterservice.entity.Monster;
import by.paul.monsterservice.entity.SearcherCriteria;
import by.paul.monsterservice.service.dto.GeneratedMonsterDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface MonsterService {
  ResponseEntity<String> addMonster(Monster monster) ;
  ResponseEntity<List<GeneratedMonsterDTO>> getGeneratedMonsters(EncounterBuilder encounterBuilder);
  ResponseEntity<List<GeneratedMonsterDTO>> getMonsterCriteria(SearcherCriteria searcherCriteria);
  ResponseEntity<List<GeneratedMonsterDTO>> getAllAuthorMonster(String authorName);
  ResponseEntity<Monster> getMonsterCriteria(String monsterName);
}
