package by.paul.monsterservice.service.servicelogic.generator;

import by.paul.monsterservice.entity.EncounterBuilder;
import by.paul.monsterservice.service.dto.GeneratedMonsterDTO;
import java.util.List;

public interface EncounterGenerator {
  List<GeneratedMonsterDTO> getGeneratedMonsters(EncounterBuilder encounterBuilder);
}
