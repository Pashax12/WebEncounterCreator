package by.paul.monsterservice.service.dto;

import by.paul.monsterservice.entity.Article;
import by.paul.monsterservice.entity.ArticleDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ArticleMapper{

  ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

  ArticleDTO articleToArticleDto(Article article);
}
