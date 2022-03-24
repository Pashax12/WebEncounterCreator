package by.paul.monsterservice.service.monster;

import by.paul.monsterservice.dto.DTOConverter;
import by.paul.monsterservice.dto.GeneratedMonsterDTO;
import by.paul.monsterservice.entity.EncounterSettings;
import by.paul.monsterservice.entity.Monster;
import by.paul.monsterservice.entity.SearchCriteria;
import by.paul.monsterservice.repository.MonsterRepository;
import by.paul.monsterservice.repository.specification.CriteriaSpecificationImpl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

  @Value("${projectData.monster.unique}")
  private String uniqueResponse;
  @Value("${projectData.monster.notUnique}")
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

  private List<Monster> generateEncounter(EncounterSettings encounterSettings) {
    return monsterEncounterGenerator.monstersListGenerator(monsterRepository.
            findAllByMonsterChallengeBetweenOrderByMonsterId(
                expCounter.getHoleFightExp(encounterSettings.getPlayersLevel(),
                    encounterSettings.getDifficulty()) / monsterCount,
                expCounter.getHoleFightExp(encounterSettings.getPlayersLevel(),
                    encounterSettings.getDifficulty()) + bufDifficulty),
        encounterSettings.isMixedTypes(), expCounter
            .getHoleFightExp(encounterSettings.getPlayersLevel(), encounterSettings.getDifficulty())
            + bufDifficulty);
  }

  public List<GeneratedMonsterDTO> getGeneratedMonsters(EncounterSettings encounterSettings) {
    return convertedMonsterDTOS.convertMonsterToMonsterDto(generateEncounter(encounterSettings));
  }


  public Map<String, String> addMonster(Monster monster) {
    Map<String, String> response = new HashMap<>();
    if (!checkIsMonsterUniqueByMonsterName(monster.getMonsterName())) {
      addHomebrewMonster(monster);
      response.put("status", uniqueResponse);
    } else {
      response.put("status", unUniqueResponse);
    }
    return response;
  }

  private boolean checkIsMonsterUniqueByMonsterName(String monsterName) {
    return monsterRepository.existsByMonsterName(monsterName);
  }

  private void addHomebrewMonster(Monster monster) {
    monsterRepository.save(monster);
  }
}
