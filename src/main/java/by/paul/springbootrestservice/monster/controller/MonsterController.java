package by.paul.springbootrestservice.monster.controller;

import by.paul.springbootrestservice.monster.entity.EncounterBuilder;
import by.paul.springbootrestservice.monster.entity.Monster;
import by.paul.springbootrestservice.monster.entity.SearcherCriteria;
import by.paul.springbootrestservice.monster.service.MonsterServiceImpl;
import by.paul.springbootrestservice.monster.service.dto.GeneratedMonsterDTO;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MonsterController {

  // В чем разница между controller vs RestController
  private final MonsterServiceImpl monsterServiceImpl;

  @PostMapping("/UserMonsterCreator/addUserMonster")
  public ResponseEntity<String> addMonster(@Valid @RequestBody Monster monster) {
    return monsterServiceImpl.addMonster(monster);
  }

  @PostMapping("/EncounterGenerator/createEncounter")
  public ResponseEntity<List<GeneratedMonsterDTO>> createEncounter(@Valid
  @RequestBody EncounterBuilder encounterBuilder) {
    return monsterServiceImpl.getGeneratedMonsters(encounterBuilder);
  }
  //превратить в гет и билдит поиск
  //
  @PostMapping("/MonsterLibrary/postMonsters")
  public ResponseEntity<List<Monster>> getMonsters(
      @Valid @RequestBody SearcherCriteria searcherCriteria) {
    return monsterServiceImpl.getMonsterByName(searcherCriteria);
  }

  @GetMapping("/MonsterLibrary/getMonster/{path}")
  public ResponseEntity<Monster> getMonsterByName(@PathVariable @Valid @NotBlank String path) {
    return monsterServiceImpl.getMonsterByName(path);
  }

  @GetMapping("/MonsterLibrary/searchMonster")
  public ResponseEntity<List<GeneratedMonsterDTO>> getAllAuthorMonster(@Valid @NotBlank
  @RequestParam(value = "author", defaultValue = "admin") String authorName) {
    return monsterServiceImpl.getAllAuthorMonster(authorName);
  }

}
