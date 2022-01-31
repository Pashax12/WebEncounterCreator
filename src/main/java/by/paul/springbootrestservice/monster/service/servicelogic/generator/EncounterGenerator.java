package by.paul.springbootrestservice.monster.service.servicelogic.generator;

import by.paul.springbootrestservice.monster.entity.EncounterBuilder;
import by.paul.springbootrestservice.monster.service.dto.GeneratedMonsterDTO;
import java.util.List;

public interface EncounterGenerator {
  List<GeneratedMonsterDTO> getGeneratedMonsters(EncounterBuilder encounterBuilder);
}
