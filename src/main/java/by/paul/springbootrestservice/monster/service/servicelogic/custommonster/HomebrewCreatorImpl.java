package by.paul.springbootrestservice.monster.service.servicelogic.custommonster;

import by.paul.springbootrestservice.monster.entity.Monster;
import by.paul.springbootrestservice.monster.repository.MonsterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomebrewCreatorImpl implements HomebrewCreator{

  private final MonsterRepository monsterRepository;

  @Override
  public boolean uniqueChecker(String monsterName, String monsterOwner) {
    return monsterRepository.existsByMonsterNameAndMonsterOwner(monsterName, monsterOwner);
  }
  @Override
  public void addMonster(Monster monster) {
    monsterRepository.save(monster);
  }

}
