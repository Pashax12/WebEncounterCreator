package by.paul.monsterservice.service.monster;

import by.paul.monsterservice.dto.DTOConverter;
import by.paul.monsterservice.dto.GeneratedMonsterDTO;
import by.paul.monsterservice.entity.EncounterSetup;
import by.paul.monsterservice.entity.Monster;
import by.paul.monsterservice.entity.SearchCriteria;
import by.paul.monsterservice.repository.MonsterRepository;
import by.paul.monsterservice.repository.specification.CriteriaSpecificationImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MonsterService {

  private final MonsterRepository monsterRepository;
  private final DTOConverter convertedMonsterDTOS;
  private final CriteriaSpecificationImpl criteriaSpecificationImpl;
  private final MonsterEncounterGenerator monsterEncounterGenerator;
  private final ExpCounter expCounter;

  @Value("${projectData.addMonster.unique}")
  private String uniqueResponse;
  @Value("${projectData.addMonster.notUnique}")
  private String unUniqueResponse;
  @Value("${projectData.generator.monsterCount}")
  private int monsterCount;
  @Value("${projectData.generator.bufDifficulty}")
  private int bufDifficulty;


  public List<GeneratedMonsterDTO> getMonsterByCriteria(SearchCriteria searchCriteria) {
    return convertedMonsterDTOS.convertMonsterToMonsterDto(
        monsterRepository.findAll(criteriaSpecificationImpl.searchSpecification(searchCriteria)));
  }

  public List<GeneratedMonsterDTO> getAllAuthorMonster(String authorName) {
    return convertedMonsterDTOS.convertMonsterToMonsterDto(
        monsterRepository.findAllByMonsterOwnerIsContainingOrderByMonsterId(authorName));
  }

  public Monster getMonsterByName(String monsterName) {
    if (monsterName.contains("_")) {
      monsterName = monsterName.replace("_", " ");
    }
    return monsterRepository.findByMonsterName(monsterName);
  }

  private List<Monster> generateEncounter(EncounterSetup encounterSetup) {
    return monsterEncounterGenerator.monstersListGenerator(monsterRepository.
            findAllByMonsterChallengeBetweenOrderByMonsterId(
                expCounter.getHoleFightExp(encounterSetup.getPlayersLevel(),
                    encounterSetup.getDifficulty()) / monsterCount,
                expCounter.getHoleFightExp(encounterSetup.getPlayersLevel(),
                    encounterSetup.getDifficulty()) + bufDifficulty),
        encounterSetup.isMixedTypes(), expCounter
            .getHoleFightExp(encounterSetup.getPlayersLevel(), encounterSetup.getDifficulty())
            + bufDifficulty);
  }

  public List<GeneratedMonsterDTO> getGeneratedMonsters(EncounterSetup encounterSetup) {
    return convertedMonsterDTOS.convertMonsterToMonsterDto(generateEncounter(encounterSetup));
  }


  public String addMonster(Monster monster) {
    if (!checkUnique(monster.getMonsterName())) {
      addHomebrew(monster);
      return uniqueResponse;
    }
    return unUniqueResponse;
  }

  private boolean checkUnique(String monsterName) {
    return monsterRepository.existsByMonsterName(monsterName);
  }

  private void addHomebrew(Monster monster) {
    monsterRepository.save(monster);
  }
}
