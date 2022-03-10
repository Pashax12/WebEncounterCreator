package by.paul.monsterservice.repository;

import by.paul.monsterservice.entity.Monster;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface MonsterRepository extends CrudRepository<Monster, Long>,
    JpaSpecificationExecutor<Monster> {

  Boolean existsByMonsterName(String monsterName);

  List<Monster> findAll(Specification<Monster> spec);

  List<Monster> findAllByMonsterChallengeBetweenOrderByMonsterId(
      int monsterChallenge, int monsterChallenge2);

  List<Monster> findAllByMonsterOwnerIsContainingOrderByMonsterId(String monsterOwner);

  Monster findByMonsterName(String monsterName);

}