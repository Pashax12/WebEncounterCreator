package by.paul.monsterservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "article_list")
@NotNull
public class Article {

  @Id
  @JsonProperty("articleId")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long articleId;
  @Column
  @JsonProperty("title")
  @NotNull(message = "title can't be null")
  @NotEmpty(message = "title can't be empty")
  private String title;
  @JsonProperty("introduction")
  @NotNull(message = "introduction can't be null")
  @NotEmpty(message = "introduction can't be empty")
  private String introduction;
  @JsonProperty("content")
  @NotNull(message = "content can't be null")
  @NotEmpty(message = "content can't be empty")
  private String content;
  @JsonProperty("uploadDate")
  @NotNull(message = "uploadDate can't be null")
  private Date uploadDate;

}
