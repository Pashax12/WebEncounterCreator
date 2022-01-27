package by.paul.springbootrestservice.monster.controller;

import by.paul.springbootrestservice.monster.entity.EncounterBuilder;
import by.paul.springbootrestservice.monster.entity.Monster;
import by.paul.springbootrestservice.monster.entity.MonsterResponse;
import by.paul.springbootrestservice.monster.entity.SearcherCriteria;
import by.paul.springbootrestservice.monster.service.MonsterService;
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

  private final MonsterService monsterService;

  @PostMapping("/UserMonsterCreator/addUserMonster")
  public ResponseEntity<String> createEncounter(@Valid @RequestBody Monster monster) {
    MonsterResponse monsterResponse = monsterService.addMonster(monster);
    return new ResponseEntity<>(monsterResponse.getStatusString(), monsterResponse.getStatusCode());
  }

  @PostMapping("/EncounterGenerator/createEncounter")
  public List<GeneratedMonsterDTO> createEncounter(@Valid
  @RequestBody EncounterBuilder encounterBuilder) {
    return monsterService.getGeneratedMonsters(encounterBuilder);
  }

  @PostMapping("/MonsterLibrary/postMonsters")
  public List<Monster> getMonsters(@Valid @RequestBody  SearcherCriteria searcherCriteria) {
    return monsterService.getMonsterFromLibrary(searcherCriteria);
  }

  @GetMapping("/MonsterLibrary/getMonster/{path}")
  public Monster getMonsterByName(@PathVariable @Valid @NotBlank String path) {
    return monsterService.getMonsterFromLibrary(path);
  }

  @GetMapping("/MonsterLibrary/searchMonster")
  public List<GeneratedMonsterDTO> getAllAuthorMonster(@Valid @NotBlank
  @RequestParam(value = "author", defaultValue = "admin") String authorName) {
    return monsterService.getAllAuthorMonster(authorName);
  }

}
