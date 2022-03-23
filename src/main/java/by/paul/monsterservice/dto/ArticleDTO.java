package by.paul.monsterservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.sql.Date;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Data
public class ArticleDTO {

  @JsonProperty("articleId")
  private Long articleId;
  @JsonProperty("title")
  private String title;
  @JsonProperty("introduction")
  private String introduction;
  @JsonProperty("uploadDate")
  private Date uploadDate;
}
