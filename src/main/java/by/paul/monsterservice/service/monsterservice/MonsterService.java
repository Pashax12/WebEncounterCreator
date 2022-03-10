package by.paul.monsterservice.service.monsterservice;

import by.paul.monsterservice.dto.DTOConverter;
import by.paul.monsterservice.dto.GeneratedMonsterDTO;
import by.paul.monsterservice.entity.EncounterBuilder;
import by.paul.monsterservice.entity.Monster;
import by.paul.monsterservice.entity.SearcherCriteria;
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


  public List<GeneratedMonsterDTO> getMonsterByCriteria(SearcherCriteria searcherCriteria) {
    return convertedMonsterDTOS.convertMonsterToMonsterDto(
        monsterRepository.findAll(criteriaSpecificationImpl.searchSpecification(searcherCriteria)));
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

  public List<Monster> generateEncounter(EncounterBuilder encounterBuilder) {
    return monsterEncounterGenerator.monstersListGenerator(monsterRepository.
            findAllByMonsterChallengeBetweenOrderByMonsterId(
                expCounter.getHoleFightExp(encounterBuilder.getPlayersLevel(),
                    encounterBuilder.getDifficulty()) / monsterCount,
                expCounter.getHoleFightExp(encounterBuilder.getPlayersLevel(),
                    encounterBuilder.getDifficulty()) + bufDifficulty),
        encounterBuilder.isMixedTypes(), expCounter
            .getHoleFightExp(encounterBuilder.getPlayersLevel(), encounterBuilder.getDifficulty())
            + bufDifficulty);
  }

  public List<GeneratedMonsterDTO> getGeneratedMonsters(EncounterBuilder encounterBuilder) {
    return convertedMonsterDTOS.convertMonsterToMonsterDto(generateEncounter(encounterBuilder));
  }


  public String addMonster(Monster monster) {
    if (!uniqueChecker(monster.getMonsterName())) {
      addHomebrew(monster);
      return uniqueResponse;
    }
    return unUniqueResponse;
  }

  public boolean uniqueChecker(String monsterName) {
    return monsterRepository.existsByMonsterName(monsterName);
  }

  public void addHomebrew(Monster monster) {
    monsterRepository.save(monster);
  }
}
