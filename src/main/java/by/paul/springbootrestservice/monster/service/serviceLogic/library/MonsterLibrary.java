package by.paul.springbootrestservice.monster.service.serviceLogic.library;

import by.paul.springbootrestservice.monster.entity.Monster;
import by.paul.springbootrestservice.monster.entity.SearcherCriteria;
import by.paul.springbootrestservice.monster.repository.MonsterRepository;
import by.paul.springbootrestservice.monster.service.dto.DTOConverter;
import by.paul.springbootrestservice.monster.service.dto.GeneratedMonsterDTO;
import by.paul.springbootrestservice.monster.service.specification.CriteriaSpecification;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MonsterLibrary {
  private final MonsterRepository monsterRepository;
  private final DTOConverter convertedMonsterDTOS;
  private final CriteriaSpecification criteriaSpecification;

  public List<Monster> getMonsterByName(SearcherCriteria searcherCriteria){
    return monsterRepository.findAll(criteriaSpecification.searchSpecification(searcherCriteria));
  }

  public List<GeneratedMonsterDTO> getAllAuthorMonster(String authorName) {
    return convertedMonsterDTOS.convertMonsterDtoToMonster(monsterRepository.findAllByMonsterOwnerIsContainingOrderByMonsterId(authorName));
  }
  public Monster getMonsterByName(String monsterName){
    if(monsterName.contains("_")){
      monsterName= monsterName.replace("_"," ");
    }
    return monsterRepository.findByMonsterName(monsterName);
  }
}
