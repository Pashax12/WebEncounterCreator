package by.paul.monsterservice.controller;

import by.paul.monsterservice.dto.GeneratedMonsterDTO;
import by.paul.monsterservice.entity.EncounterSettings;
import by.paul.monsterservice.entity.Monster;
import by.paul.monsterservice.entity.SearchCriteria;
import by.paul.monsterservice.service.monster.MonsterService;
import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@CrossOrigin
public class MonsterController {

  private final MonsterService monsterService;

  @PostMapping("/monster")
  @PreAuthorize("hasAuthority('access:write')")
  public ResponseEntity<Monster> addMonster(@Valid @RequestBody Monster monster) {
    return ResponseEntity.ok(monsterService.addMonster(monster));
  }

  @PostMapping("/encounter-generator")
  public ResponseEntity<List<GeneratedMonsterDTO>> createEncounter(@Valid
  @RequestBody EncounterSettings encounterSettings) {
    return ResponseEntity.ok(monsterService.getGeneratedMonsters(encounterSettings));
  }

  @PostMapping("/monsterlibrary")
  public ResponseEntity<List<GeneratedMonsterDTO>> getMonsters(
      @Valid @RequestBody SearchCriteria searchCriteria) {
    return ResponseEntity.ok(monsterService.getMonsterByCriteria(searchCriteria));
  }

  @GetMapping("/monsterlibrary/{name}")
  public ResponseEntity<Monster> getMonsterByName(@PathVariable @Valid @NotBlank String name) {
    return ResponseEntity.ok(monsterService.getMonsterByName(name));
  }
}
