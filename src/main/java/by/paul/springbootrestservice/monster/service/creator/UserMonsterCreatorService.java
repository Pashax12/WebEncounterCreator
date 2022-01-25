package by.paul.springbootrestservice.monster.service.creator;

import by.paul.springbootrestservice.monster.entity.Monster;
import by.paul.springbootrestservice.monster.repository.MonsterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserMonsterCreatorService {


  private final MonsterRepository monsterRepository;

  public void addMonster(Monster monster) {
    monster.setMonsterOwner("Homebrew: ".concat(monster.getMonsterOwner()));
    monsterRepository.save(monster);
  }


}
