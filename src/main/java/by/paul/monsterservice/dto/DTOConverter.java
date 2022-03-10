package by.paul.monsterservice.dto;

import by.paul.monsterservice.dto.mapper.ArticleMapper;
import by.paul.monsterservice.dto.mapper.MonsterMapper;
import by.paul.monsterservice.entity.Article;
import by.paul.monsterservice.entity.Monster;
import java.util.List;
import java.util.stream.Collectors;
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
