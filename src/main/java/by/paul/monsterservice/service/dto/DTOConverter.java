package by.paul.monsterservice.service.dto;

import by.paul.monsterservice.entity.Article;
import by.paul.monsterservice.entity.ArticleDTO;
import by.paul.monsterservice.entity.GeneratedMonsterDTO;
import by.paul.monsterservice.entity.Monster;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


@Service
public class DTOConverter {

  public List<GeneratedMonsterDTO> convertMonsterToMonsterDto(List<Monster> monsters) {
    return monsters.stream().map(MonsterMapper.INSTANCE::monsterToMonsterDto)
        .collect(Collectors.toList());
  }

  public List<ArticleDTO> convertArticleToArticleDto(List<Article> articles) {
    return articles.stream().map(ArticleMapper.INSTANCE::articleToArticleDto)
        .collect(Collectors.toList());

  }
}
