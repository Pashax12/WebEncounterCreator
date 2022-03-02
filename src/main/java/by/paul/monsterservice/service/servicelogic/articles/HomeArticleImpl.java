package by.paul.monsterservice.service.servicelogic.articles;

import by.paul.monsterservice.entity.Article;
import by.paul.monsterservice.entity.ArticleDTO;
import by.paul.monsterservice.repository.ArticleRepository;
import by.paul.monsterservice.service.dto.DTOConverter;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HomeArticleImpl implements HomeArticle {

  private final ArticleRepository articleRepository;
  private final DTOConverter dtoConverter;

  @Override
  public List<ArticleDTO> getLastArticles() {
    return dtoConverter.convertArticleToArticleDto(articleRepository.findTop5ByOrderByUploadDate());
  }

  @Override
  public Article getArticleById(Long articleId) {
    return articleRepository.findArticleByArticleId(articleId);
  }
}
