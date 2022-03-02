package by.paul.monsterservice.service.servicelogic.custommonster;

import by.paul.monsterservice.entity.Monster;
import by.paul.monsterservice.repository.MonsterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomebrewCreatorImpl implements HomebrewCreator{

  private final MonsterRepository monsterRepository;

  @Override
  public boolean uniqueChecker(String monsterName) {
    return monsterRepository.existsByMonsterName(monsterName);
  }
  @Override
  public void addMonster(Monster monster) {
    monsterRepository.save(monster);
  }

}
