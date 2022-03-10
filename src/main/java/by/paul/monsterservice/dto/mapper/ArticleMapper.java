package by.paul.monsterservice.dto.mapper;

import by.paul.monsterservice.dto.ArticleDTO;
import by.paul.monsterservice.entity.Article;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ArticleMapper {

  ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

  ArticleDTO articleToArticleDto(Article article);
}
