package by.paul.monsterservice.service.servicelogic.generator;

import by.paul.monsterservice.entity.EncounterBuilder;
import by.paul.monsterservice.entity.Monster;
import by.paul.monsterservice.repository.MonsterRepository;
import by.paul.monsterservice.service.dto.DTOConverter;
import by.paul.monsterservice.entity.GeneratedMonsterDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EncounterGeneratorImpl implements EncounterGenerator{

  @Value("${projectData.generator.monsterCount}")
  private int monsterCount;
  @Value("${projectData.generator.bufDifficulty}")
  private int bufDifficulty;

  private final MonsterRepository monsterRepository;
  private final DTOConverter convertedMonsterDTOS;
  private final MonsterEncounterGenerator monsterEncounterGenerator;
  private final ExpCounter expCounter;

  public List<Monster> generateEncounter(EncounterBuilder encounterBuilder) {
    return monsterEncounterGenerator.monstersListGenerator(monsterRepository.
            findAllByMonsterChallengeBetweenOrderByMonsterId(
                expCounter.getHoleFightExp(encounterBuilder.getPlayersLevel(), encounterBuilder.getDifficulty()) / monsterCount,
                expCounter.getHoleFightExp(encounterBuilder.getPlayersLevel(), encounterBuilder.getDifficulty()) + bufDifficulty),
        encounterBuilder.isMixedTypes(), expCounter.getHoleFightExp(encounterBuilder.getPlayersLevel(), encounterBuilder.getDifficulty()) + bufDifficulty);
  }
  @Override
  public List<GeneratedMonsterDTO> getGeneratedMonsters(EncounterBuilder encounterBuilder) {
    return convertedMonsterDTOS.convertMonsterToMonsterDto(generateEncounter(encounterBuilder));
  }
}
