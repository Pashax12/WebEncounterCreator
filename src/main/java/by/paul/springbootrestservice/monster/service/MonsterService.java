package by.paul.springbootrestservice.monster.service;

import by.paul.springbootrestservice.monster.entity.EncounterBuilder;
import by.paul.springbootrestservice.monster.entity.Monster;
import by.paul.springbootrestservice.monster.entity.MonsterResponse;
import by.paul.springbootrestservice.monster.entity.SearcherCriteria;
import by.paul.springbootrestservice.monster.service.dto.GeneratedMonsterDTO;
import by.paul.springbootrestservice.monster.service.serviceLogic.customMonster.HomebrewCreator;
import by.paul.springbootrestservice.monster.service.serviceLogic.generator.EncounterGenerator;
import by.paul.springbootrestservice.monster.service.serviceLogic.library.MonsterLibrary;
import java.util.List;
import lombok.RequiredArgsConstructor;
import net.bytebuddy.agent.builder.AgentBuilder.LambdaInstrumentationStrategy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MonsterService {

  private final EncounterGenerator encounterGenerator;
  private final HomebrewCreator homebrewCreator;
  private final MonsterLibrary monsterLibrary;

  public ResponseEntity<String> addMonster(Monster monster) {
    monster.setMonsterOwner("Homebrew: ".concat(monster.getMonsterOwner()));
    if(!homebrewCreator.uniqueChecker(monster.getMonsterName(), monster.getMonsterOwner())){
      homebrewCreator.addMonster(monster);
      return new ResponseEntity<>("Monster successfully added", HttpStatus.CREATED);
    }
    return new ResponseEntity<>("Monster is not unique", HttpStatus.ACCEPTED);
  }

  public ResponseEntity<List<GeneratedMonsterDTO>> getGeneratedMonsters(EncounterBuilder encounterBuilder){
    return new ResponseEntity<>(encounterGenerator.getGeneratedMonsters(encounterBuilder), HttpStatus.FOUND);
  }

  public ResponseEntity<List<Monster>> getMonsterByName(SearcherCriteria searcherCriteria){
    return new ResponseEntity<>(monsterLibrary.getMonsterByName(searcherCriteria),HttpStatus.FOUND);
  }

  public ResponseEntity<List<GeneratedMonsterDTO>> getAllAuthorMonster(String authorName) {
    return new ResponseEntity<>(monsterLibrary.getAllAuthorMonster(authorName),HttpStatus.FOUND);
  }
  public ResponseEntity<Monster> getMonsterByName(String monsterName){
    return new ResponseEntity<>(monsterLibrary.getMonsterByName(monsterName),HttpStatus.FOUND);
  }
}
