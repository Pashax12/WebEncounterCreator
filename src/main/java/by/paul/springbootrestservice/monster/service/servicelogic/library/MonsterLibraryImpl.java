package by.paul.springbootrestservice.monster.service.servicelogic.library;

import by.paul.springbootrestservice.monster.entity.Monster;
import by.paul.springbootrestservice.monster.entity.SearcherCriteria;
import by.paul.springbootrestservice.monster.repository.MonsterRepository;
import by.paul.springbootrestservice.monster.service.dto.DTOConverter;
import by.paul.springbootrestservice.monster.service.dto.GeneratedMonsterDTO;
import by.paul.springbootrestservice.monster.service.specification.CriteriaSpecificationImpl;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MonsterLibraryImpl implements MonsterLibrary {

  private final MonsterRepository monsterRepository;
  private final DTOConverter convertedMonsterDTOS;
  private final CriteriaSpecificationImpl criteriaSpecificationImpl;

  @Override
  public List<Monster> getMonsterByName(SearcherCriteria searcherCriteria) {
    return monsterRepository.findAll(criteriaSpecificationImpl.searchSpecification(searcherCriteria));
  }

  @Override
  public List<GeneratedMonsterDTO> getAllAuthorMonster(String authorName) {
    return convertedMonsterDTOS.convertMonsterDtoToMonster(
        monsterRepository.findAllByMonsterOwnerIsContainingOrderByMonsterId(authorName));
  }

  @Override
  public Monster getMonsterByName(String monsterName) {
    if (monsterName.contains("_")) {
      monsterName = monsterName.replace("_", " ");
    }
    return monsterRepository.findByMonsterName(monsterName);
  }
}
