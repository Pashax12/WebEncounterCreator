package by.paul.monsterservice.repository.specification;

import by.paul.monsterservice.entity.Monster;
import by.paul.monsterservice.entity.Monster_;
import by.paul.monsterservice.entity.SearcherCriteria;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriteriaSpecificationImpl implements CriteriaSpecification {

  public Specification<Monster> searchSpecification(SearcherCriteria criteria) {

    return (root, query, criteriaBuilder) -> {

      List<Predicate> predicateList = new ArrayList<>();

      if (criteria.getMonsterType() != null) {
        predicateList.add(criteriaBuilder
            .like(root.get(Monster_.MONSTER_META), "%" + criteria.getMonsterType() + "%"));
      }
      if (criteria.getMonsterSize() != null) {
        predicateList.add(criteriaBuilder
            .like(root.get(Monster_.MONSTER_META), criteria.getMonsterSize() + '%'));
      }
      if (criteria.getMonsterOutlook() != null) {
        predicateList.add(criteriaBuilder
            .like(root.get(Monster_.MONSTER_META), "%" + criteria.getMonsterOutlook()));
      }
      if (criteria.getMinMonsterChallenge() >= 0) {
        predicateList.add(criteriaBuilder
            .greaterThanOrEqualTo(root.get(Monster_.MONSTER_CHALLENGE),
                criteria.getMinMonsterChallenge()));
      }
      if (criteria.getMaxMonsterChallenge() <= 155000) {
        predicateList.add(criteriaBuilder
            .lessThanOrEqualTo(root.get(Monster_.MONSTER_CHALLENGE),
                criteria.getMaxMonsterChallenge()));
      } else if (criteria.getMaxMonsterChallenge() == 0) {
        predicateList.add(criteriaBuilder
            .lessThanOrEqualTo(root.get(Monster_.MONSTER_CHALLENGE), 155000));
      }

      if (!criteria.isSource()) {
        predicateList.add(criteriaBuilder
            .equal(root.get(Monster_.MONSTER_OWNER), "admin"));
      }
      if (criteria.isLegendaryAction()) {
        predicateList.add(criteriaBuilder
            .isNotNull(root.get(Monster_.MONSTER_LEGENDARY_ACTIONS)));
      }
      if (criteria.isSpecialSkills()) {
        predicateList.add(criteriaBuilder
            .isNotNull(root.get(Monster_.MONSTER_SKILLS)));
      }

      return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
    };

  }
}
