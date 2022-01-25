package by.paul.springbootrestservice.monster.service.library.specification;

import by.paul.springbootrestservice.monster.entity.Monster;
import by.paul.springbootrestservice.monster.entity.Monster_;
import by.paul.springbootrestservice.monster.entity.SearcherCriteria;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CriteriaSpecification {

  public Specification<Monster> searchSpecification(SearcherCriteria criteria) {

    return (root, query, criteriaBuilder) -> {

      if (criteria.getMonsterType() != null) {
        query = query.where(criteriaBuilder
            .like(root.get(Monster_.MONSTER_META), "%" + criteria.getMonsterType() + "%"));
      }
      if (criteria.getMonsterSize() != null) {
        query = query.where(criteriaBuilder
            .like(root.get(Monster_.MONSTER_META), criteria.getMonsterType() + "%"));
      }
      if (criteria.getMinMonsterChallenge() >= 0) {
        query = query.where(criteriaBuilder
            .lessThanOrEqualTo(root.get(Monster_.MONSTER_CHALLENGE), criteria.getMinMonsterChallenge()));
      }
      if(criteria.getMaxMonsterChallenge() <= 30 ){
        query = query.where(criteriaBuilder
            .greaterThanOrEqualTo(root.get(Monster_.MONSTER_CHALLENGE), criteria.getMaxMonsterChallenge()));
      }
      if (criteria.getMonsterOutlook() != null) {
        query = query.where(criteriaBuilder
            .like(root.get(Monster_.MONSTER_META), "%" + criteria.getMonsterType()));
      }
      if (criteria.getMinMonsterSpeed() >= 0) {
        query = query.where(criteriaBuilder
            .lessThanOrEqualTo(root.get(Monster_.MONSTER_SPEED), criteria.getMinMonsterSpeed()));
      }
      if(criteria.getMaxMonsterSpeed() <= 200){
        query = query.where(criteriaBuilder
            .greaterThanOrEqualTo(root.get(Monster_.MONSTER_SPEED), criteria.getMaxMonsterSpeed()));
      }
      if (!criteria.isSource()) {
        query = query.where(criteriaBuilder
            .notLike(root.get(Monster_.MONSTER_OWNER), "Homebrew %"));
      }
      if (!criteria.isLegendaryAction()) {
        query = query.where(criteriaBuilder
            .equal(root.get(Monster_.MONSTER_LEGENDARY_ACTIONS), null));
      }
      if (!criteria.isSpecialSkills()) {
        query = query.where(criteriaBuilder
            .equal(root.get(Monster_.MONSTER_SKILLS), null));
      }
      return query.getGroupRestriction();//Посмотреть отличия
    };

  }
}
