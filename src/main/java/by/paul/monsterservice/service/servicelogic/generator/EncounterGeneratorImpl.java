package by.paul.monsterservice.service.servicelogic.generator;

import by.paul.monsterservice.entity.EncounterBuilder;
import by.paul.monsterservice.entity.Monster;
import by.paul.monsterservice.repository.MonsterRepository;
import by.paul.monsterservice.service.dto.DTOConverter;
import by.paul.monsterservice.service.dto.GeneratedMonsterDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EncounterGeneratorImpl implements EncounterGenerator{

  private final MonsterRepository monsterRepository;
  private final DTOConverter convertedMonsterDTOS;
  private final MonsterEncounterGenerator monsterEncounterGenerator;
  private final ExpCounter expCounter;

  public List<Monster> generateEncounter(EncounterBuilder encounterBuilder) {
    return monsterEncounterGenerator.monstersListGenerator(monsterRepository.
            findAllByMonsterChallengeBetweenAndMonsterOwnerOrderByMonsterId(
                expCounter.getHoleFightExp(encounterBuilder.getPlayersLevel(), encounterBuilder.getDifficulty()) / 5,
                expCounter.getHoleFightExp(encounterBuilder.getPlayersLevel(), encounterBuilder.getDifficulty()) + 100, encounterBuilder.getMonsterOwner()),
        encounterBuilder.isMixedTypes(), expCounter.getHoleFightExp(encounterBuilder.getPlayersLevel(), encounterBuilder.getDifficulty()) + 100);
  }
  @Override
  public List<GeneratedMonsterDTO> getGeneratedMonsters(EncounterBuilder encounterBuilder) {
    return convertedMonsterDTOS.convertMonsterDtoToMonster(generateEncounter(encounterBuilder));
  }
}
