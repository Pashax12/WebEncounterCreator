package by.paul.monsterservice.service.article;

import by.paul.monsterservice.dto.ArticleDTO;
import by.paul.monsterservice.dto.DTOConverter;
import by.paul.monsterservice.entity.Article;
import by.paul.monsterservice.repository.ArticleRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeArticle {

  private final ArticleRepository articleRepository;
  private final DTOConverter dtoConverter;

  public List<ArticleDTO> getLastArticles() {
    return dtoConverter.convertArticleToArticleDto(articleRepository.findTop5ByOrderByUploadDate());
  }

  public Article getArticleById(Long articleId) {
    return articleRepository.findArticleByArticleId(articleId);
  }
}
