package by.paul.monsterservice.service.servicelogic.articles;

import by.paul.monsterservice.entity.Article;
import by.paul.monsterservice.entity.ArticleDTO;
import java.util.List;

public interface HomeArticle {
   List<ArticleDTO> getLastArticles();
   Article getArticleById(Long articleId);

}
