package by.paul.monsterservice.controller;

import by.paul.monsterservice.entity.EncounterBuilder;
import by.paul.monsterservice.entity.Monster;
import by.paul.monsterservice.entity.SearcherCriteria;
import by.paul.monsterservice.service.MonsterService;
import by.paul.monsterservice.service.dto.GeneratedMonsterDTO;
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

  @PostMapping("/usermonster")
  public ResponseEntity<String> addMonster(@Valid @RequestBody Monster monster) {
    return monsterService.addMonster(monster);
  }

  @PostMapping("/createencounter")
  public ResponseEntity<List<GeneratedMonsterDTO>> createEncounter(@Valid
  @RequestBody EncounterBuilder encounterBuilder) {
    return monsterService.getGeneratedMonsters(encounterBuilder);
  }

  @PostMapping("/monsterlibrary")
  public ResponseEntity<List<Monster>> getMonsters(@Valid @RequestBody  SearcherCriteria searcherCriteria) {
    return monsterService.getMonsterCriteria(searcherCriteria);
  }

  @GetMapping("/monsterlibrary/{path}")
  public ResponseEntity<Monster> getMonsterByName(@PathVariable @Valid @NotBlank String path) {
    return monsterService.getMonsterCriteria(path);
  }

  @GetMapping("/monsterlibrary")
  public ResponseEntity<List<GeneratedMonsterDTO>> getAllAuthorMonster(@Valid @NotBlank
  @RequestParam(value = "author", defaultValue = "admin") String authorName) {
    return monsterService.getAllAuthorMonster(authorName);
  }

}
