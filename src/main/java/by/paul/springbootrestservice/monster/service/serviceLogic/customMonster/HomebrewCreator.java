package by.paul.springbootrestservice.monster.service.serviceLogic.customMonster;

import by.paul.springbootrestservice.monster.entity.Monster;
import by.paul.springbootrestservice.monster.repository.MonsterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HomebrewCreator {
  private final MonsterRepository monsterRepository;

  public boolean uniqueChecker(String monsterName, String monsterOwner){
    return monsterRepository.existsByMonsterNameAndMonsterOwner(monsterName, monsterOwner);
  }
  public void addMonster(Monster monster){

    monsterRepository.save(monster);
  }

}
