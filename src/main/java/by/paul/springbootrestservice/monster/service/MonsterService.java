package by.paul.springbootrestservice.monster.service;

import by.paul.springbootrestservice.monster.entity.EncounterBuilder;
import by.paul.springbootrestservice.monster.entity.Monster;
import by.paul.springbootrestservice.monster.entity.SearcherCriteria;
import by.paul.springbootrestservice.monster.service.dto.GeneratedMonsterDTO;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface MonsterService {
  ResponseEntity<String> addMonster(Monster monster) ;
  ResponseEntity<List<GeneratedMonsterDTO>> getGeneratedMonsters(EncounterBuilder encounterBuilder);
  ResponseEntity<List<Monster>> getMonsterByName(SearcherCriteria searcherCriteria);
  ResponseEntity<List<GeneratedMonsterDTO>> getAllAuthorMonster(String authorName);
  ResponseEntity<Monster> getMonsterByName(String monsterName);
}
