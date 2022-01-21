package by.paul.springbootrestservice.monster.controller;

import by.paul.springbootrestservice.monster.entity.Monster;
import by.paul.springbootrestservice.monster.entity.SearcherCriteria;
import by.paul.springbootrestservice.monster.service.generator.EncounterGeneratorService;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/MonsterLibrary")
public class EncounterGenerator {
  private final EncounterGeneratorService encounterGeneratorService;

  @PostMapping("/postMonsters")
  public List<Monster> createEncounter(@RequestBody SearcherCriteria searcherCriteria) {
    return new ArrayList<>();
  }

}
