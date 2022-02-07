package by.paul.monsterservice.service;

import by.paul.monsterservice.entity.EncounterBuilder;
import by.paul.monsterservice.entity.Monster;
import by.paul.monsterservice.entity.SearcherCriteria;
import by.paul.monsterservice.service.dto.GeneratedMonsterDTO;
import by.paul.monsterservice.service.servicelogic.custommonster.HomebrewCreator;
import by.paul.monsterservice.service.servicelogic.generator.EncounterGenerator;
import by.paul.monsterservice.service.servicelogic.library.MonsterLibrary;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MonsterServiceImpl implements MonsterService {

  private final EncounterGenerator encounterGenerator;
  private final HomebrewCreator homebrewCreator;
  private final MonsterLibrary monsterLibrary;

  @Override
  public ResponseEntity<String> addMonster(Monster monster) {
    monster.setMonsterOwner("Homebrew: ".concat(monster.getMonsterOwner()));
    if (!homebrewCreator.uniqueChecker(monster.getMonsterName(), monster.getMonsterOwner())) {
      homebrewCreator.addMonster(monster);
      return new ResponseEntity<>("Monster successfully added", HttpStatus.CREATED);
    }
    return new ResponseEntity<>("Monster is not unique", HttpStatus.ACCEPTED);
  }

  @Override
  public ResponseEntity<List<GeneratedMonsterDTO>> getGeneratedMonsters(
      EncounterBuilder encounterBuilder) {
    return new ResponseEntity<>(encounterGenerator.getGeneratedMonsters(encounterBuilder),
        HttpStatus.FOUND);
  }

  @Override
  public ResponseEntity<List<Monster>> getMonsterCriteria(SearcherCriteria searcherCriteria) {
    return new ResponseEntity<>(monsterLibrary.getMonsterByName(searcherCriteria),
        HttpStatus.FOUND);
  }

  @Override
  public ResponseEntity<List<GeneratedMonsterDTO>> getAllAuthorMonster(String authorName) {
    return new ResponseEntity<>(monsterLibrary.getAllAuthorMonster(authorName), HttpStatus.FOUND);
  }

  @Override
  public ResponseEntity<Monster> getMonsterCriteria(String monsterName) {
    return new ResponseEntity<>(monsterLibrary.getMonsterByName(monsterName), HttpStatus.FOUND);
  }
}
