package by.paul.springbootrestservice.monster.service.servicelogic.generator;

import by.paul.springbootrestservice.monster.entity.EncounterBuilder;
import by.paul.springbootrestservice.monster.entity.Monster;
import by.paul.springbootrestservice.monster.repository.MonsterRepository;
import by.paul.springbootrestservice.monster.service.dto.DTOConverter;
import by.paul.springbootrestservice.monster.service.dto.GeneratedMonsterDTO;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EncounterGeneratorImpl implements EncounterGenerator{

  private final MonsterRepository monsterRepository;
  private final DTOConverter convertedMonsterDTOS;
  private final MonsterEncounterGenerator monsterEncounterGenerator;

  public List<Monster> generateEncounter(EncounterBuilder encounterBuilder) {
    return monsterEncounterGenerator.monstersListGenerator(monsterRepository.
            findAllByMonsterChallengeBetweenAndMonsterOwnerOrderByMonsterId(
                encounterBuilder.getHoleFightExp() / 5,
                encounterBuilder.getHoleFightExp() + 100, encounterBuilder.getMonsterOwner()),
        encounterBuilder.isMixedTypes(), encounterBuilder.getHoleFightExp() + 100);
  }
  @Override
  public List<GeneratedMonsterDTO> getGeneratedMonsters(EncounterBuilder encounterBuilder) {
    return convertedMonsterDTOS.convertMonsterDtoToMonster(generateEncounter(encounterBuilder));
  }
}
