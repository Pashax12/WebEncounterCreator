package by.paul.springbootrestservice.monster.service.library;

import by.paul.springbootrestservice.monster.dao.MonsterRepository;
import by.paul.springbootrestservice.monster.entity.Monster;
import by.paul.springbootrestservice.monster.entity.SearcherCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MonsterLibraryService {

  private final MonsterRepository monsterRepository;

  public List<Monster> getMonsterFromLibrary(SearcherCriteria searcherCriteria){
    return monsterRepository.findAllByParams("");
  }

}
