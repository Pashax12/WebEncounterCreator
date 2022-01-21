package by.paul.springbootrestservice.monster.service.generator;


import by.paul.springbootrestservice.monster.dao.MonsterRepository;
import by.paul.springbootrestservice.monster.entity.EncounterBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GeneratorManager {

  private final MonsterRepository monsterRepository;
  private final EncounterBuilder encounterBuilder;
  private final MonsterMessageGenerator monsterMessageGenerator;
/*

  //вынести логику в  monsterMessageGenerator
  public String generateEncounter() {
    return monsterMessageGenerator.monstersListGenerator(monsterRepository.
            findAllByMonsterChallengeBetweenOrderByMonsterChallenge(
                encounterBuilder.getHoleFightExp() / 5,
                encounterBuilder.getHoleFightExp() + 100),
        encounterBuilder.isMixedTypes(), encounterBuilder.getHoleFightExp() + 100);
  }
*/
}
