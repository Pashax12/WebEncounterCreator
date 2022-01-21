package by.paul.springbootrestservice.monster.dao;

import by.paul.springbootrestservice.monster.entity.Monster;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface MonsterRepository extends CrudRepository<Monster, Long> {

  List<Monster> findAllByParams(String param);//переделать

  List<Monster> findAllByMonsterChallengeBetweenAndMonsterOwnerOrderByMonsterId(
      int monsterChallenge, int monsterChallenge2, String monsterOwner);

  List<Monster> findAllByMonsterOwnerOrderByMonsterId(String monsterOwner);
}