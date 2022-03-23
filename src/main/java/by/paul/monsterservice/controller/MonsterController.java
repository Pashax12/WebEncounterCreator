package by.paul.monsterservice.controller;

import by.paul.monsterservice.dto.GeneratedMonsterDTO;
import by.paul.monsterservice.entity.EncounterSetup;
import by.paul.monsterservice.entity.Monster;
import by.paul.monsterservice.entity.SearchCriteria;
import by.paul.monsterservice.service.monster.MonsterService;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@CrossOrigin
public class MonsterController {

  private final MonsterService monsterService;

  @PostMapping("/usermonster")
  @PreAuthorize("hasAuthority('access:write')")
  public ResponseEntity<Map<String, String>> addMonster(@Valid @RequestBody Monster monster) {
    return ResponseEntity.ok(monsterService.addMonster(monster));
  }

  @PostMapping("/createencounter")
  public ResponseEntity<List<GeneratedMonsterDTO>> createEncounter(@Valid
  @RequestBody EncounterSetup encounterSetup) {
    return ResponseEntity.ok(monsterService.getGeneratedMonsters(encounterSetup));
  }

  @PostMapping("/monsterlibrary")
  public ResponseEntity<List<GeneratedMonsterDTO>> getMonsters(
      @Valid @RequestBody SearchCriteria searchCriteria) {
    return ResponseEntity.ok(monsterService.getMonsterByCriteria(searchCriteria));
  }

  @GetMapping("/monsterlibrary/{path}")
  public ResponseEntity<Monster> getMonsterByName(@PathVariable @Valid @NotBlank String path) {
    return ResponseEntity.ok(monsterService.getMonsterByName(path));
  }

  @GetMapping("/monsterlibrary")
  public ResponseEntity<List<GeneratedMonsterDTO>> getAllAuthorMonster(@Valid @NotBlank
  @RequestParam(value = "author", defaultValue = "admin") String authorName) {
    return ResponseEntity.ok(monsterService.getAllAuthorMonster(authorName));
  }

}
