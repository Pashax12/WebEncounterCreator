package by.paul.springbootrestservice.monster.service;

import by.paul.springbootrestservice.monster.entity.EncounterBuilder;
import by.paul.springbootrestservice.monster.entity.Monster;
import by.paul.springbootrestservice.monster.entity.SearcherCriteria;
import by.paul.springbootrestservice.monster.service.dto.GeneratedMonsterDTO;
import by.paul.springbootrestservice.monster.service.servicelogic.custommonster.HomebrewCreatorImpl;
import by.paul.springbootrestservice.monster.service.servicelogic.generator.EncounterGeneratorImpl;
import by.paul.springbootrestservice.monster.service.servicelogic.library.MonsterLibraryImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MonsterServiceImpl implements MonsterService{

  //сделать интерфейс
  private final EncounterGeneratorImpl encounterGeneratorImpl;
  private final HomebrewCreatorImpl homebrewCreatorImpl;
  private final MonsterLibraryImpl monsterLibraryImpl;

  @Override
  public ResponseEntity<String> addMonster(Monster monster) {
    monster.setMonsterOwner("Homebrew: ".concat(monster.getMonsterOwner()));
    if (!homebrewCreatorImpl.uniqueChecker(monster.getMonsterName(), monster.getMonsterOwner())) {
      homebrewCreatorImpl.addMonster(monster);
      return new ResponseEntity<>("Monster successfully added", HttpStatus.CREATED);
    }
    return new ResponseEntity<>("Monster is not unique", HttpStatus.ACCEPTED);
  }
  @Override
  public ResponseEntity<List<GeneratedMonsterDTO>> getGeneratedMonsters(
      EncounterBuilder encounterBuilder) {
    return new ResponseEntity<>(encounterGeneratorImpl.getGeneratedMonsters(encounterBuilder),
        HttpStatus.FOUND);
  }
  @Override
  public ResponseEntity<List<Monster>> getMonsterByName(SearcherCriteria searcherCriteria) {
    return new ResponseEntity<>(monsterLibraryImpl.getMonsterByName(searcherCriteria),
        HttpStatus.FOUND);
  }
  @Override
  public ResponseEntity<List<GeneratedMonsterDTO>> getAllAuthorMonster(String authorName) {
    return new ResponseEntity<>(monsterLibraryImpl.getAllAuthorMonster(authorName), HttpStatus.FOUND);
  }
  @Override
  public ResponseEntity<Monster> getMonsterByName(String monsterName) {
    return new ResponseEntity<>(monsterLibraryImpl.getMonsterByName(monsterName), HttpStatus.FOUND);
  }
}
