package by.paul.springbootrestservice.monsterService.repository;

import by.paul.springbootrestservice.monsterService.entity.Monster;
import java.util.List;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

public interface MonsterRepository extends CrudRepository<Monster, Long>,
    JpaSpecificationExecutor<Monster> {

  List<Monster> findAll( Specification<Monster> spec );//Page - почитать

  List<Monster> findAllByMonsterChallengeBetweenAndMonsterOwnerOrderByMonsterId(
      int monsterChallenge, int monsterChallenge2, String monsterOwner);

  List<Monster> findAllByMonsterOwnerIsContainingOrderByMonsterId(String monsterOwner);

  Monster findByMonsterName(String monsterName);
  //
  //PatternDao - PatterReposity
}