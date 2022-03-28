package by.paul.monsterservice;

import by.paul.monsterservice.dto.ArticleDTO;
import by.paul.monsterservice.dto.GeneratedMonsterDTO;
import by.paul.monsterservice.dto.mapper.ArticleMapper;
import by.paul.monsterservice.dto.mapper.MonsterMapper;
import by.paul.monsterservice.entity.Article;
import by.paul.monsterservice.entity.Monster;
import java.sql.Date;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MapperTest {

  private static Article article;
  private static Monster monster;

  @BeforeAll
  public static void init() {
    article = new Article();
    article.setArticleId(1L);
    article.setTitle("Test text");
    article.setIntroduction("Some introduction");
    article.setUploadDate(new Date(2004, 7, 12));

    monster = new Monster();
    monster.setMonsterName("Test monster");
    monster.setMonsterMeta("Test meta");
  }

  @Test
  void isArticleInstanceOFArticleDTO() {
    Assertions
        .assertInstanceOf(ArticleDTO.class, ArticleMapper.INSTANCE.articleToArticleDto(article));
  }

  @Test
  void isArticleDTOSameTitle() {
    Assertions
        .assertEquals(ArticleMapper.INSTANCE.articleToArticleDto(article).getTitle(),
            "Test text");
  }

  @Test
  void isArticleDTOSameUploadData() {
    Assertions
        .assertEquals(ArticleMapper.INSTANCE.articleToArticleDto(article).getUploadDate(),
            new Date(2004, 7, 12));
  }

  @Test
  void isMonsterInstanceOFGeneratedMonsterDTO() {
    Assertions
        .assertInstanceOf(GeneratedMonsterDTO.class,
            MonsterMapper.INSTANCE.monsterToMonsterDto(monster));
  }

  @Test
  void isGeneratedMonsterSameName() {
    Assertions
        .assertEquals(MonsterMapper.INSTANCE.monsterToMonsterDto(monster).getMonsterName(),
            "Test monster");
  }

  @Test
  void isGeneratedMonsterSameMeta() {
    Assertions
        .assertEquals(MonsterMapper.INSTANCE.monsterToMonsterDto(monster).getMonsterMeta(),
            "Test meta");
  }
}
