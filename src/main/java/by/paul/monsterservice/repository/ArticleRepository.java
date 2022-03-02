package by.paul.monsterservice.repository;

import by.paul.monsterservice.entity.Article;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {

  List<Article> findTop5ByOrderByUploadDate();

  Article findArticleByArticleId(Long articleId);
}
