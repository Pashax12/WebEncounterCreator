package by.paul.monsterservice.service;

import by.paul.monsterservice.entity.EncounterBuilder;
import by.paul.monsterservice.entity.Monster;
import by.paul.monsterservice.entity.SearcherCriteria;
import by.paul.monsterservice.entity.GeneratedMonsterDTO;
import by.paul.monsterservice.service.servicelogic.custommonster.HomebrewCreator;
import by.paul.monsterservice.service.servicelogic.generator.EncounterGenerator;
import by.paul.monsterservice.service.servicelogic.library.MonsterLibrary;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MonsterServiceImpl implements MonsterService {

  @Value("${projectData.addMonster.unique}")
  private String uniqueResponse;

  @Value("${projectData.addMonster.notUnique}")
  private String unUniqueResponse;

  private final EncounterGenerator encounterGenerator;
  private final HomebrewCreator homebrewCreator;
  private final MonsterLibrary monsterLibrary;

  @Override
  public ResponseEntity<String> addMonster(Monster monster) {
    if (!homebrewCreator.uniqueChecker(monster.getMonsterName())){
      homebrewCreator.addMonster(monster);
      return new ResponseEntity<>(uniqueResponse, HttpStatus.OK);
    }
    return new ResponseEntity<>(unUniqueResponse, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<GeneratedMonsterDTO>> getGeneratedMonsters(
      EncounterBuilder encounterBuilder) {
    return new ResponseEntity<>(encounterGenerator.getGeneratedMonsters(encounterBuilder),
        HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<GeneratedMonsterDTO>> getMonsterCriteria(SearcherCriteria searcherCriteria) {
    return new ResponseEntity<>(monsterLibrary.getMonsterByName(searcherCriteria),
        HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<GeneratedMonsterDTO>> getAllAuthorMonster(String authorName) {
    return new ResponseEntity<>(monsterLibrary.getAllAuthorMonster(authorName), HttpStatus.OK);
  }

  @Override
  public ResponseEntity<Monster> getMonsterCriteria(String monsterName) {
    return new ResponseEntity<>(monsterLibrary.getMonsterByName(monsterName), HttpStatus.OK);
  }
}
