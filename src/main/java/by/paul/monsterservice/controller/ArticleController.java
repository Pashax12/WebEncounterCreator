package by.paul.monsterservice.controller;

import by.paul.monsterservice.dto.ArticleDTO;
import by.paul.monsterservice.entity.Article;
import by.paul.monsterservice.service.article.HomeArticle;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@CrossOrigin
public class ArticleController {

  private final HomeArticle homeArticle;

  @GetMapping("/article")
  public ResponseEntity<List<ArticleDTO>> getAllAuthorMonster() {
    return new ResponseEntity<>(homeArticle.getLastArticles(), HttpStatus.OK);
  }

  @GetMapping("/article/{path}")
  public ResponseEntity<Article> getAllAuthorMonster(@PathVariable @Valid @NotBlank Long path) {
    return new ResponseEntity<>(homeArticle.getArticleById(path), HttpStatus.OK);
  }
}
