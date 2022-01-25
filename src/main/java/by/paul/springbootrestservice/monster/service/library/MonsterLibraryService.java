package by.paul.springbootrestservice.monster.service.library;

import by.paul.springbootrestservice.monster.entity.SearcherCriteria;
import by.paul.springbootrestservice.monster.entity.dto.GeneratedMonsterDTO;
import by.paul.springbootrestservice.monster.repository.MonsterRepository;
import by.paul.springbootrestservice.monster.entity.Monster;
import by.paul.springbootrestservice.monster.entity.dto.DTOConverter;
import by.paul.springbootrestservice.monster.service.library.specification.CriteriaSpecification;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MonsterLibraryService {

  private final MonsterRepository monsterRepository;
  private final DTOConverter convertedMonsterDTOS;
  private final CriteriaSpecification criteriaSpecification;

  public List<Monster> getMonsterFromLibrary(SearcherCriteria searcherCriteria){
    return monsterRepository.findAll(criteriaSpecification.searchSpecification(searcherCriteria));
  }

  public List<GeneratedMonsterDTO> getAllAuthorMonster(String authorName) {
    return convertedMonsterDTOS.convertMonsterDtoToMonster(monsterRepository.findAllByMonsterOwnerIsContainingOrderByMonsterId(authorName));
  }
  public Monster getMonsterFromLibrary(String monsterName){
    return monsterRepository.findByMonsterName(monsterName);
  }
}
