package by.paul.monsterservice.repository.specification;

import by.paul.monsterservice.entity.Monster;
import by.paul.monsterservice.entity.Monster_;
import by.paul.monsterservice.entity.SearchCriteria;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.hibernate.criterion.CriteriaSpecification;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CriteriaSpecificationImpl implements CriteriaSpecification {

  @Value("${projectData.specification.baseOwner}")
  private String baseOwner;
  @Value("${projectData.specification.maxChallenge}")
  private int maxChallenge;
  @Value("${projectData.specification.minChallenge}")
  private int minChallenge;


  public Specification<Monster> searchSpecification(SearchCriteria criteria) {

    return (root, query, criteriaBuilder) -> {

      List<Predicate> predicateList = new ArrayList<>();
      Optional.ofNullable(checkMonsterType(criteria, criteriaBuilder, root)).ifPresent(
          predicateList::add);
      Optional.ofNullable(checkMonsterSize(criteria, criteriaBuilder, root)).ifPresent(
          predicateList::add);
      Optional.ofNullable(checkMonsterOutlook(criteria, criteriaBuilder, root)).ifPresent(
          predicateList::add);
      Optional.ofNullable(checkMonsterMinChallenge(criteria, criteriaBuilder, root)).ifPresent(
          predicateList::add);
      Optional.ofNullable(checkMonsterMaxChallenge(criteria, criteriaBuilder, root)).ifPresent(
          predicateList::add);
      Optional.ofNullable(checkSource(criteria, criteriaBuilder, root)).ifPresent(
          predicateList::add);
      Optional.ofNullable(checkLegendaryAction(criteria, criteriaBuilder, root)).ifPresent(
          predicateList::add);
      Optional.ofNullable(checkSpecialSkills(criteria, criteriaBuilder, root)).ifPresent(
          predicateList::add);

      return criteriaBuilder.and(predicateList.toArray(new Predicate[predicateList.size()]));
    };
  }

  private Predicate checkMonsterType(SearchCriteria criteria, CriteriaBuilder criteriaBuilder,
      Root root) {
    if (criteria.getMonsterType() != null && !criteria.getMonsterType().equals("")) {
      return criteriaBuilder
          .like(root.get(Monster_.MONSTER_META), "%" + criteria.getMonsterType() + "%");
    }
    return null;
  }

  private Predicate checkMonsterSize(SearchCriteria criteria, CriteriaBuilder criteriaBuilder,
      Root root) {
    if (criteria.getMonsterSize() != null && !criteria.getMonsterSize().equals("")) {
      return criteriaBuilder
          .like(root.get(Monster_.MONSTER_META), criteria.getMonsterSize() + '%');
    }
    return null;
  }

  private Predicate checkMonsterOutlook(SearchCriteria criteria, CriteriaBuilder criteriaBuilder,
      Root root) {
    if (criteria.getMonsterOutlook() != null && !criteria.getMonsterOutlook().equals("")) {
      return criteriaBuilder
          .like(root.get(Monster_.MONSTER_META), "%" + criteria.getMonsterOutlook());
    }
    return null;
  }

  private Predicate checkMonsterMinChallenge(SearchCriteria criteria,
      CriteriaBuilder criteriaBuilder, Root root) {
    if (criteria.getMinMonsterChallenge() >= minChallenge) {
      return criteriaBuilder.greaterThanOrEqualTo(root.get(Monster_.MONSTER_CHALLENGE),
          criteria.getMinMonsterChallenge());
    }
    return null;
  }

  private Predicate checkMonsterMaxChallenge(SearchCriteria criteria,
      CriteriaBuilder criteriaBuilder, Root root) {
    if (criteria.getMaxMonsterChallenge() <= maxChallenge
        && criteria.getMaxMonsterChallenge() > minChallenge) {
      return criteriaBuilder.lessThanOrEqualTo(root.get(Monster_.MONSTER_CHALLENGE),
          criteria.getMaxMonsterChallenge());
    } else if (criteria.getMaxMonsterChallenge() == minChallenge) {
      return criteriaBuilder.lessThanOrEqualTo(root.get(Monster_.MONSTER_CHALLENGE), maxChallenge);
    }
    return null;
  }

  private Predicate checkSource(SearchCriteria criteria, CriteriaBuilder criteriaBuilder,
      Root root) {
    if (!criteria.isSource()) {
      return criteriaBuilder.equal(root.get(Monster_.MONSTER_OWNER), baseOwner);
    }
    return null;
  }

  private Predicate checkLegendaryAction(SearchCriteria criteria, CriteriaBuilder criteriaBuilder,
      Root root) {
    if (criteria.isLegendaryAction()) {
      return criteriaBuilder.isNotNull(root.get(Monster_.MONSTER_LEGENDARY_ACTIONS));
    }
    return null;
  }

  private Predicate checkSpecialSkills(SearchCriteria criteria, CriteriaBuilder criteriaBuilder,
      Root root) {

    if (criteria.isSpecialSkills()) {
      return criteriaBuilder.isNotNull(root.get(Monster_.MONSTER_SKILLS));
    }
    return null;
  }
}
