package by.paul.springbootrestservice.monster.service.generator;


import by.paul.springbootrestservice.monster.repository.MonsterRepository;
import by.paul.springbootrestservice.monster.entity.EncounterBuilder;
import by.paul.springbootrestservice.monster.entity.dto.GeneratedMonsterDTO;
import by.paul.springbootrestservice.monster.entity.Monster;
import by.paul.springbootrestservice.monster.entity.dto.DTOConverter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class GeneratorService {

  private final MonsterRepository monsterRepository;
  private final MonsterEncounterGenerator monsterEncounterGenerator;
  private final DTOConverter dtoConverter;


  public List<Monster> generateEncounter(EncounterBuilder encounterBuilder) {
    return monsterEncounterGenerator.monstersListGenerator(monsterRepository.
            findAllByMonsterChallengeBetweenAndMonsterOwnerOrderByMonsterId(
                encounterBuilder.getHoleFightExp() / 5,
                encounterBuilder.getHoleFightExp() + 100, encounterBuilder.getMonsterOwner()),
        encounterBuilder.isMixedTypes(), encounterBuilder.getHoleFightExp() + 100);
  }

  public List<GeneratedMonsterDTO> getGeneratedMonsters(EncounterBuilder encounterBuilder){
    return dtoConverter.convertMonsterDtoToMonster(generateEncounter(encounterBuilder));
  }
}
