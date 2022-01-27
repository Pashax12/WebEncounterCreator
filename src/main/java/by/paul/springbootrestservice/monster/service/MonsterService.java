package by.paul.springbootrestservice.monster.service;

import by.paul.springbootrestservice.monster.entity.EncounterBuilder;
import by.paul.springbootrestservice.monster.entity.Monster;
import by.paul.springbootrestservice.monster.entity.MonsterResponse;
import by.paul.springbootrestservice.monster.entity.SearcherCriteria;
import by.paul.springbootrestservice.monster.service.dto.DTOConverter;
import by.paul.springbootrestservice.monster.service.dto.GeneratedMonsterDTO;
import by.paul.springbootrestservice.monster.repository.MonsterRepository;
import by.paul.springbootrestservice.monster.service.specification.CriteriaSpecification;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MonsterService {
  private final MonsterRepository monsterRepository;
  private final DTOConverter convertedMonsterDTOS;
  private final CriteriaSpecification criteriaSpecification;
  private final MonsterEncounterGenerator monsterEncounterGenerator;

  public MonsterResponse addMonster(Monster monster) {
    if(!monsterRepository.existsByMonsterNameAndMonsterOwner(monster.getMonsterName(), monster.getMonsterOwner())){
      monster.setMonsterOwner("Homebrew: ".concat(monster.getMonsterOwner()));
      monsterRepository.save(monster);
      return MonsterResponse.builder().statusString("Monster successfully added").statusCode(HttpStatus.CREATED).build();
    }
    return MonsterResponse.builder().statusString("Monster is not unique").statusCode(HttpStatus.ACCEPTED).build();
  }
  public List<Monster> generateEncounter(EncounterBuilder encounterBuilder) {
    return monsterEncounterGenerator.monstersListGenerator(monsterRepository.
            findAllByMonsterChallengeBetweenAndMonsterOwnerOrderByMonsterId(
                encounterBuilder.getHoleFightExp() / 5,
                encounterBuilder.getHoleFightExp() + 100, encounterBuilder.getMonsterOwner()),
        encounterBuilder.isMixedTypes(), encounterBuilder.getHoleFightExp() + 100);
  }

  public List<GeneratedMonsterDTO> getGeneratedMonsters(EncounterBuilder encounterBuilder){
    return convertedMonsterDTOS.convertMonsterDtoToMonster(generateEncounter(encounterBuilder));
  }
  public List<Monster> getMonsterFromLibrary(SearcherCriteria searcherCriteria){
    return monsterRepository.findAll(criteriaSpecification.searchSpecification(searcherCriteria));
  }

  public List<GeneratedMonsterDTO> getAllAuthorMonster(String authorName) {
    return convertedMonsterDTOS.convertMonsterDtoToMonster(monsterRepository.findAllByMonsterOwnerIsContainingOrderByMonsterId(authorName));
  }
  public Monster getMonsterFromLibrary(String monsterName){
    if(monsterName.contains("_")){
      monsterName= monsterName.replace("_"," ");
    }
    return monsterRepository.findByMonsterName(monsterName);
  }
}
