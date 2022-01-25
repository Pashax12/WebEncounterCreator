package by.paul.springbootrestservice.monster.controller;

import by.paul.springbootrestservice.monster.entity.EncounterBuilder;
import by.paul.springbootrestservice.monster.entity.dto.GeneratedMonsterDTO;
import by.paul.springbootrestservice.monster.service.generator.GeneratorService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/EncounterGenerator")
@RequiredArgsConstructor
public class EncounterGenerator {

  private final GeneratorService generatorService;

  @PostMapping("/createEncounter")
  public List<GeneratedMonsterDTO> createEncounter(@RequestBody EncounterBuilder encounterBuilder) {

    return generatorService.getGeneratedMonsters(encounterBuilder);

  }

}
